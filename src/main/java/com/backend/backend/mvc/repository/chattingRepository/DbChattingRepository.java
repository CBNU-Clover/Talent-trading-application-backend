package com.backend.backend.mvc.repository.chattingRepository;

import com.backend.backend.mvc.domain.chat.chattingRoom.ChattingRoom;
import com.backend.backend.mvc.domain.chat.message.ChatMessage;
import com.backend.backend.mvc.domain.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.mvc.domain.chat.chattingRoom.QChattingRoom.chattingRoom;
import static com.backend.backend.mvc.domain.chat.message.QMessage.message;

@Repository
public class DbChattingRepository implements ChattingRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbChattingRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Long saveRoom(ChattingRoom chattingRoom) {
        em.persist(chattingRoom);
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
                .select(message)
                .from(message)
                .where(message.room.id.eq(roomId))
                .orderBy(message.id.desc()) //나중에 생긴 것일 수록 숫자가 큼
                .fetch();
    }

    @Override
    public List<ChattingRoom> findChattingRoomByMember(Member member) {
        return query
                .select(chattingRoom)
                .from(chattingRoom)
                .where(chattingRoom.buyer.id.eq(member.getId())
                        .or(chattingRoom.seller.id.eq(member.getId())))
                .fetch();
    }
}
