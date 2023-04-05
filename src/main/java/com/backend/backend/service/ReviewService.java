package com.backend.backend.service;


import com.backend.backend.domain.Member;
import com.backend.backend.domain.Review;
import com.backend.backend.exception.NotExistException;
import com.backend.backend.exception.PermissionDeniedException;
import com.backend.backend.repository.postRepository.PostRepository;
import com.backend.backend.repository.reviewRepository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;


    /**
     * review객체 전달시 저장
     * @param review
     * @return
     */
    @Transactional
    public Long writeReview(Review review){
        return reviewRepository.save(review);
    }

    /**
     * review를 구분 가능한 방법이 id뿐이기에 id로 찾음
     * @param id
     * @return
     */
    public Review readReview(Long id){
        return reviewRepository.findReviewById(id);
    }

    /**
     * review를 구분 가능한 방법이 id뿐이기에 id로 검색하여 종료
     * 해당 Member의 리뷰가 아닐 경우 삭제 불가능
     * @param id
     */
    @Transactional
    public void deleteReview(Long id, Member member){
        Review review = reviewRepository.findReviewById(id);
        if(review==null){
            throw new NotExistException("존재하지 않는 리뷰 입니다");
        }
        if(review.getWriter().getId()== member.getId()) {
            reviewRepository.deleteReviewById(id);
        }
        else {
            throw new PermissionDeniedException("해당 멤버에게 이 리뷰를 삭제할 권한이 없습니다");
        }
    }
}
