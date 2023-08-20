package com.backend.backend.mvc.repository.reviewRepository;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.review.Review;

import java.util.List;

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

    /**
     * post의 별점 평균 반환
     * @param postId 게시글 id
     * @return 리뷰 별점 평균
     */
    Double getReviewStarRatingAvg(Long postId);

    /**
     * 해당 member가 작성한 리뷰 반환
     * @param member 작성자
     * @return
     */
    List<Review> findReviewsByMember(Member member);

    /**
     * 해당 post에 작성된 리뷰 반환
     * @param post 리뷰가 작성된 게시글
     * @return
     */
    List<Review> findReviewsByPost(Post post);
}
