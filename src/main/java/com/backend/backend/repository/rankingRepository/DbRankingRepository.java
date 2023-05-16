package com.backend.backend.repository.rankingRepository;

import com.backend.backend.domain.member.Rating;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static com.backend.backend.domain.member.QRating.rating;

public class DbRankingRepository implements RankingRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbRankingRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Rating> getTopRanking(RankingCategory category) {
        return null;
    }
}
