package com.backend.backend.mvc.repository.reviewRepository;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.review.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.mvc.domain.review.QReview.review;


@Repository
public class DbReviewRepository implements ReviewRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbReviewRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public Long save(Review review) {
        em.persist(review);
        return review.getId();
    }

    @Override
    public Review findReviewById(Long id) {
        return em.find(Review.class, id);
    }

    @Override
    public void deleteReviewById(Long id) {
        Review review = em.find(Review.class, id);
        em.remove(review);
    }

    @Override
    public Double getReviewStarRatingAvg(Long postId) {
        return null;
    }

    @Override
    public List<Review> findReviewsByMember(Member member) {
        return query
                .select(review)
                .from(review)
                .where(review.writer.id.eq(member.getId()))
                .orderBy(review.date.desc())
                .limit(100)
                .fetch();
    }

    @Override
    public List<Review> findReviewsByPost(Post post) {
        return query
                .select(review)
                .from(review)
                .where(review.post.id.eq(post.getId()))
                .orderBy(review.date.desc())
                .limit(100)
                .fetch();
    }
}
