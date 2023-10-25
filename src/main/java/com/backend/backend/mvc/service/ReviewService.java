package com.backend.backend.mvc.service;


import com.backend.backend.common.DataProcessing.TokenParsing;
import com.backend.backend.mvc.controller.post.Dto.PostGetAllBoard;
import com.backend.backend.mvc.controller.review.dto.ReviewReadResponse;
import com.backend.backend.mvc.controller.review.dto.ReviewWriteRequest;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.review.Review;
import com.backend.backend.common.exception.NotExistException;
import com.backend.backend.common.exception.PermissionDeniedException;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.repository.reviewRepository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    /**
     * ReviewWriteRequest객체 전달시 review 저장
     * @param reviewWriteRequest
     * @return
     */
    @Transactional
    public Long writeReview(ReviewWriteRequest reviewWriteRequest,String nickname){
        Review review = Review.builder()
                .post(postRepository.findPostById(reviewWriteRequest.getPostId()))
                .writer(memberRepository
                        .findMemberByNickname(nickname))
                .content(reviewWriteRequest.getContent())
                .starRating(reviewWriteRequest.getStarRating())
                .build();

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
     * @param name
     */
    @Transactional
    public void deleteReview(Long id , String name){
        Review review = reviewRepository.findReviewById(id);
        Member member=memberRepository.findMemberByNickname(name);
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

    @Transactional
    public List<ReviewReadResponse> getAllPostReview(Long postId)
    {

        List<ReviewReadResponse> all_review=new ArrayList<>();
        Post post=postRepository.findPostById(postId);
        List<Review> reviews=reviewRepository.findReviewsByPost(post);
        for(int num=0;num<reviews.size();num++)
        {
            all_review.add(num,new ReviewReadResponse(reviews.get(num),reviews.get(num).getWriter().getImage().getId().toString()));
        }

        return all_review;
    }

    @Transactional
    public List<ReviewReadResponse> getAllMyReview(String nickname)
    {

        List<ReviewReadResponse> all_review=new ArrayList<>();
        List<Review> reviews=new ArrayList<>();
        Member member=memberRepository.findMemberByNickname(nickname);
        reviews=reviewRepository.findReviewsByMember(member);

        for(int num=0 ; num<reviews.size();num++)
        {
            all_review.add(num,new ReviewReadResponse(reviews.get(num),member.getImage().getId().toString()));
        }

        return all_review;
    }

    @Transactional
    public Double getReviewAvg(Long postId)
    {
        return reviewRepository.getReviewStarRatingAvg(postId);
    }
}
