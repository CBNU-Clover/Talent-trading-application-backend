package com.backend.backend.mvc.controller.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReviewWriteRequest {

    @NotNull(message = "리뷰를 작성한 게시글에 대한 정보는 반드시 입력되어야 합니다")
    private Long postId;

    private String content;

    private Long starRating;

    public ReviewWriteRequest() {
    }

    public ReviewWriteRequest(Long postId, String writerNickname, String content, Long starRating) {
        this.postId = postId;
        this.content = content;
        this.starRating = starRating;
    }
}
