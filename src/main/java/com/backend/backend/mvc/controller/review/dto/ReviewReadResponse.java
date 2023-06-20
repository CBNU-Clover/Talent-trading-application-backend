package com.backend.backend.mvc.controller.review.dto;

import com.backend.backend.mvc.domain.review.Review;
import lombok.Data;

@Data
public class ReviewReadResponse {
    private Long postId;
    private String writerNickname;
    private String content;

    public ReviewReadResponse(Review review) {
        this.postId = review.getId();
        this.writerNickname = review.getWriter().getNickname().toString();
        this.content = review.getContent();
    }
}
