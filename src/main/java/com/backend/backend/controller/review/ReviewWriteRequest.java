package com.backend.backend.controller.review;

import com.backend.backend.domain.Member;
import com.backend.backend.domain.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
