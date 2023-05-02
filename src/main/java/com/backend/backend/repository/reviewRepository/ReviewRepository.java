package com.backend.backend.repository.reviewRepository;

import com.backend.backend.domain.review.Review;

public interface ReviewRepository {
    /**
     * Review객체 전달시 레파지토리에 저장됨
     * @param review
     * @return
     */
    Long save(Review review);

    /**
     * id를 이용해서 Review객체 검색
     * @param id
     * @return
     */
    Review findReviewById(Long id);

    /**
     * id를 이용해서 Review 객체 삭제
     * @param id
     */
    void deleteReviewById(Long id);

}
