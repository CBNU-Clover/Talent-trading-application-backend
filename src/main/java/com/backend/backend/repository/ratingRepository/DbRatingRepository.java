package com.backend.backend.repository.ratingRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.member.RatingCategory;
import com.backend.backend.domain.member.Rating;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.domain.member.QMember.member;
import static com.backend.backend.domain.member.QRating.rating;

@Repository
public class DbRatingRepository implements RatingRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbRatingRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public List<Rating> getTopRanking(RatingCategory category) {
        return query
                .select(rating)
                .from(rating)
                .where(rating.category.eq(category))
                .orderBy(rating.score.desc())
                .limit(20)
                .fetch();
    }

    @Override
    public Long addRating(Member member,RatingCategory category, Long amount) {

        Rating categoryRating = query
                .select(rating)
                .from(rating)
                .where(rating.category.eq(category).and(rating.member.id.eq(member.getId())))
                .fetchOne();

        if(categoryRating==null){
            categoryRating=new Rating(member, category);
            em.persist(categoryRating);
        }

        categoryRating.addScore(amount);

        return categoryRating.getId();
    }

    @Override
    public Rating findRatingById(Long id) {
        return em.find(Rating.class,id);
    }

}
