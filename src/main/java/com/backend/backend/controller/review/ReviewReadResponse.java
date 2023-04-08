package com.backend.backend.controller.review;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReviewReadResponse {
    private Long postId;
    private String writerNickname;
    private String content;

    public ReviewReadResponse(Long postId, String writerNickname, String content) {
        this.postId = postId;
        this.writerNickname = writerNickname;
        this.content = content;
    }
}
