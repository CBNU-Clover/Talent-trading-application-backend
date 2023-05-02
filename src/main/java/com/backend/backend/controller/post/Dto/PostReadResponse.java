package com.backend.backend.controller.post.Dto;

import com.backend.backend.domain.post.Post;
import lombok.Data;

@Data
public class PostReadResponse {
    private String  WriterNickname;
    private String postName;

    private String content;

    public PostReadResponse(Post post) {
        this.WriterNickname = post.getWriter().getNickname();
        this.postName = post.getPostName();
        this.content = post.getContent();
    }
}
