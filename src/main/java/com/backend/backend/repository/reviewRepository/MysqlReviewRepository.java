package com.backend.backend.repository.reviewRepository;

import com.backend.backend.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MysqlReviewRepository implements ReviewRepository{

    private final EntityManager em;
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
}
