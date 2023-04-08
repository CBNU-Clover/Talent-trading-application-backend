package com.backend.backend.controller.review;

import com.backend.backend.domain.Review;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReviewReadResponse {
    private Long postId;
    private String writerNickname;
    private String content;

    public ReviewReadResponse(Review review) {
        this.postId = review.getId();
        this.writerNickname = review.getWriter().getNickName();
        this.content = review.getContent();
    }
}
