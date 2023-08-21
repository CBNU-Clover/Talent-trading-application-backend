package com.backend.backend.mvc.controller.review.dto;

import com.backend.backend.mvc.domain.member.values.Nickname;
import com.backend.backend.mvc.domain.review.Review;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class ReviewReadResponse {

    private Long postId;
    private Long reviewId;
    private String writerNickname;
    private String content;

    private Long rating;
    private String date;

    public ReviewReadResponse(Review review) {
        this.postId = review.getPost().getId();
        this.reviewId = review.getId();
        this.writerNickname = review.getWriter().getNickname().toString();
        this.content = review.getContent();
        this.rating = review.getStarRating().getRating();
        this.date = review.getDate().toString().replace("T", " ");
    }
}
