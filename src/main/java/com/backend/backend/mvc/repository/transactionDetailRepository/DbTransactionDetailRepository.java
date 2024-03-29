package com.backend.backend.mvc.repository.transactionDetailRepository;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.transactionDetail.TransactionDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.mvc.domain.transactionDetail.QTransactionDetail.transactionDetail;


@Repository
public class DbTransactionDetailRepository implements TransactionDetailRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbTransactionDetailRepository(EntityManager em) {
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
