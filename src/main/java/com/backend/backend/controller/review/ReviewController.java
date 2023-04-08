package com.backend.backend.controller.review;

import com.backend.backend.controller.post.Dto.PostWriteRequest;
import com.backend.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/write")
    public ResponseEntity<Long> writeReview(@RequestBody @Valid ReviewWriteRequest reviewWriteRequest){
        Long reviewId = reviewService.writeReview(reviewWriteRequest);
        return new ResponseEntity<>(reviewId, HttpStatus.CREATED);
    }

}
