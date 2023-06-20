package com.backend.backend.mvc.repository.pointDetailRepository;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.pointDetail.PointDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.mvc.domain.pointDetail.QPointDetail.pointDetail;


@Repository
public class DbPointDetailRepository implements PointDetailRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbPointDetailRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public Long save(PointDetail pointDetail) {
        em.persist(pointDetail);
        return pointDetail.getId();
    }

    @Override
    public PointDetail findDetailById(Long id) {
        return em.find(PointDetail.class,id);
    }

    @Override
    public List<PointDetail> findDetailsByMember(Member member) {
        return query
                .select(pointDetail)
                .from(pointDetail)
                .where(pointDetail.owner.id.eq(member.getId()))
                .orderBy(pointDetail.date.desc())
                .limit(100)
                .fetch();
    }
}
