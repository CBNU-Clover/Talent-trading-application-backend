package com.backend.backend.repository.rankingRepository;

import com.backend.backend.domain.member.RatingCategory;
import com.backend.backend.domain.member.Rating;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class DbRankingRepository implements RankingRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbRankingRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Rating> getTopRanking(RatingCategory category) {
        return null;
    }
}
