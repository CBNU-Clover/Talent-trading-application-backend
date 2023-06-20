package com.backend.backend.mvc.controller.post.Dto;

import com.backend.backend.mvc.domain.post.Post;
import lombok.Data;

@Data
public class PostReadResponse {
    private String  WriterNickname;
    private String postName;

    private String content;

    public PostReadResponse(Post post) {
        this.WriterNickname = post.getWriter().getNickname().toString();
        this.postName = post.getPostName().toString();
        this.content = post.getContent().toString();
    }
}
