package com.backend.backend.controller.post.Dto;

import com.backend.backend.domain.Post;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class PostReadResponse {
    private String  WriterNickname;
    private String postName;

    private String content;

    public PostReadResponse(Post post) {
        this.WriterNickname = post.getWriter().getNickName();
        this.postName = post.getPostName();
        this.content = post.getContent();
    }
}
