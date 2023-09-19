package com.backend.backend.mvc.repository.chattingRepository;

import com.backend.backend.mvc.domain.chat.chattingRoom.ChattingRoom;
import com.backend.backend.mvc.domain.chat.chattingRoom.UserChattingRoom;
import com.backend.backend.mvc.domain.chat.message.ChatMessage;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.mvc.domain.chat.chattingRoom.QChattingRoom.chattingRoom;
import static com.backend.backend.mvc.domain.chat.chattingRoom.QUserChattingRoom.userChattingRoom;
import static com.backend.backend.mvc.domain.chat.message.QChatMessage.chatMessage;

@Repository
public class DbChattingRepository implements ChattingRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbChattingRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public ChattingRoom findChattingRoomById(Long id) {
        return em.find(ChattingRoom.class,id);
    }

    @Override
    public Long makeRoom( Post post, Member seller, Member buyer) {
        ChattingRoom chattingRoom = new ChattingRoom(post, seller, buyer);
        em.persist(chattingRoom);
        em.persist(new UserChattingRoom(seller, chattingRoom));
        em.persist(new UserChattingRoom(buyer, chattingRoom));
        return chattingRoom.getId();
    }

    @Override
    public Long saveMessage(ChatMessage chatMessage) {
        em.persist(chatMessage);
        return chatMessage.getId();
    }

    @Override
    public List<ChatMessage> findMessagesByRoomId(Long roomId) {
        return query
                .select(chatMessage)
                .from(chatMessage)
                .where(chatMessage.room.id.eq(roomId))
                .orderBy(chatMessage.id.desc()) //나중에 생긴 것일 수록 숫자가 큼
                .fetch();
    }

    @Override
    public List<ChattingRoom> findChattingRoomByMember(Member member) {
        return query
                .select(userChattingRoom.room)
                .from(userChattingRoom)
                .where(userChattingRoom.member.id.eq(member.getId()))
                .fetch();
    }

    @Override
    public void removeUserCattingRoom(Member member, ChattingRoom room) {
        if(member==null){
            throw new IllegalArgumentException("회원이 입력되지 않았습니다");
        }
        if(room==null){
            throw new IllegalArgumentException("채팅방이 입력되지 않았습니다");
        }



        //삭제
        query
                .delete(userChattingRoom)
                .where(userChattingRoom.member.id.eq(member.getId())
                        .and(userChattingRoom.room.id.eq(room.getId())))
                .execute();

        removeEmptyChatRoom(room);
    }

    private void removeEmptyChatRoom(ChattingRoom room){
        Long count = query
                .select(userChattingRoom.count())
                .from(userChattingRoom)
                .where(userChattingRoom.room.id.eq(room.getId()))
                .fetchFirst();

        if(count==0){
            em.remove(room);
        }
    }
}
