package com.backend.backend.mvc.repository.chattingRepository;

import com.backend.backend.mvc.domain.chat.chattingRoom.ChattingRoom;
import com.backend.backend.mvc.domain.chat.message.Message;
import com.backend.backend.mvc.domain.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.mvc.domain.chat.chattingRoom.QChattingRoom.chattingRoom;

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
    public Long saveMessage(Message message) {
        em.persist(message);
        return message.getId();
    }

    @Override
    public List<Message> findMessagesByRoomId(Long roomId) {
        return null;
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
