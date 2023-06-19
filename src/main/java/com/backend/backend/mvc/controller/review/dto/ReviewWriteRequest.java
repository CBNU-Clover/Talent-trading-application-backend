package com.backend.backend.mvc.controller.review.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReviewWriteRequest {

    @NotNull(message = "리뷰를 작성한 게시글에 대한 정보는 반드시 입력되어야 합니다")
    private Long postId;
    @NotEmpty(message = "리뷰를 작성한 작성자에 대한 정보는 반드시 입력되어야 합니다")
    private String writerNickname;
    private String content;

    public ReviewWriteRequest() {
    }

    public ReviewWriteRequest(Long postId, String writerNickname, String content) {
        this.postId = postId;
        this.writerNickname = writerNickname;
        this.content = content;
    }
}
