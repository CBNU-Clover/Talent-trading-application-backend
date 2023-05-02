package com.backend.backend.controller.review.dto;

import com.backend.backend.domain.review.Review;
import lombok.Data;

@Data
public class ReviewReadResponse {
    private Long postId;
    private String writerNickname;
    private String content;

    public ReviewReadResponse(Review review) {
        this.postId = review.getId();
        this.writerNickname = review.getWriter().getNickname();
        this.content = review.getContent();
    }
}
