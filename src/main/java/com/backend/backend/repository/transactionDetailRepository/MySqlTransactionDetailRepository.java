package com.backend.backend.repository.transactionDetailRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.transactionDetail.TransactionDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.domain.transactionDetail.QTransactionDetail.transactionDetail;

@Repository
public class MySqlTransactionDetailRepository implements TransactionDetailRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public MySqlTransactionDetailRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Long save(TransactionDetail transactionDetail) {
        em.persist(transactionDetail);
        return transactionDetail.getId();
    }

    @Override
    public TransactionDetail findDetailById(Long id) {
        return em.find(TransactionDetail.class,id);
    }

    @Override
    public List<TransactionDetail> findDetailsByMember(Member member) {
        return query
                .select(transactionDetail)
                .from(transactionDetail)
                .where(transactionDetail.buyer.id.eq(member.getId())
                        .or(transactionDetail.seller.id.eq(member.getId())))
                .orderBy(transactionDetail.startDate.desc())
                .limit(100)
                .fetch();
    }
}
