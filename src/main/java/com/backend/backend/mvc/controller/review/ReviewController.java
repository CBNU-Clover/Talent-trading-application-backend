package com.backend.backend.mvc.controller.review;

import com.backend.backend.common.DataProcessing.TokenParsing;
import com.backend.backend.mvc.controller.review.dto.ReviewReadResponse;
import com.backend.backend.mvc.controller.review.dto.ReviewWriteRequest;
import com.backend.backend.mvc.domain.review.Review;
import com.backend.backend.mvc.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vi/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    /**
     * 리뷰 작성 api
     * @param reviewWriteRequest
     * @return
     */
    @PostMapping("/write")
    public ResponseEntity<Long> writeReview(@RequestBody @Valid ReviewWriteRequest reviewWriteRequest,HttpServletRequest request)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        Long reviewId = reviewService.writeReview(reviewWriteRequest,result);
        return new ResponseEntity<>(reviewId, HttpStatus.CREATED);
    }

    /**
     * 리뷰 한개 읽기 api
     * @param reviewId
     * @return 
     */
    @GetMapping("/read/{reviewId}")
    public ResponseEntity<ReviewReadResponse> readPost(@PathVariable("reviewId") Long reviewId){

        Review review = reviewService.readReview(reviewId);
        ReviewReadResponse reviewReadResponse = new ReviewReadResponse(review);

        return new ResponseEntity<>(reviewReadResponse,HttpStatus.OK);
    }


    /**
     * 해당 게시물에 대한 모든 리뷰 가져오는 api
     * @param postId
     * @return
     */
    @GetMapping("/getAllPostReview/{postId}")
    public List<ReviewReadResponse> getAllPostReview(@PathVariable("postId") Long postId)
    {
        return reviewService.getAllPostReview(postId);
    }
    
    /**
     * 리뷰 삭제 api
     * @param reviewId , JWT 토큰에 들어있는 닉네임
     * @return
     */
    @GetMapping("/delete/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Long reviewId,HttpServletRequest request)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        reviewService.deleteReview(reviewId, result);
    }

    /**
     * 로그인한 사용자가 작성한 모든 리뷰 가져오는 api
     * @param request ( JWT 토큰에 들어있는 닉네임 )
     * @return
     */
    @GetMapping("/getAllMyReview")
    public List<ReviewReadResponse> getAllMyReview(HttpServletRequest request)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        return reviewService.getAllMyReview(result);
    }

    /**
     * 각 게시물의 평균 별점 나타내는 Api
     * @param postId
     * @return
     */
    @GetMapping("/getPostAvg/{postId}")
    public Double getPostAvg(@PathVariable("postId") Long postId)
    {

        return reviewService.getReviewAvg(postId);
    }
}
