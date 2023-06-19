package com.backend.backend.mvc.controller.review;

import com.backend.backend.mvc.controller.review.dto.ReviewReadResponse;
import com.backend.backend.mvc.controller.review.dto.ReviewWriteRequest;
import com.backend.backend.mvc.domain.review.Review;
import com.backend.backend.mvc.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;


    /**
     * 리뷰 작성 api
     * @param reviewWriteRequest
     * @return
     */
    @PostMapping("/write")
    public ResponseEntity<Long> writeReview(@RequestBody @Valid ReviewWriteRequest reviewWriteRequest){
        Long reviewId = reviewService.writeReview(reviewWriteRequest);
        return new ResponseEntity<>(reviewId, HttpStatus.CREATED);
    }

    @GetMapping("/read/{reviewId}")
    public ResponseEntity<ReviewReadResponse> readPost(@PathVariable("reviewId") Long reviewId){

        Review review = reviewService.readReview(reviewId);
        ReviewReadResponse reviewReadResponse = new ReviewReadResponse(review);

        return new ResponseEntity<>(reviewReadResponse,HttpStatus.OK);
    }


}
