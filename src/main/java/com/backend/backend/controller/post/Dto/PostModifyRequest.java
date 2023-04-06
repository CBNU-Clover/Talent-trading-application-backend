package com.backend.backend.controller.post.Dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PostModifyRequest {


    @NotNull(message = "수정할 게시글에 대한 정보는 반드시 들어가야 합니다")
    private Long postId;

    @NotEmpty(message = "수정자 정보는 반드시 들어가야 합니다")
    private String  modifierNickname;
    private String postName;
    private String content;
}
