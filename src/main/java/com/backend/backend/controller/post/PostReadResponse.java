package com.backend.backend.controller.post;

import com.backend.backend.domain.Post;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PostReadResponse {
    private String  nickname;
    private String postName;

    private String content;

    public PostReadResponse(Post post) {
        this.nickname = post.getWriter().getNickName();
        this.postName = post.getPostName();
        this.content = post.getContent();
    }
}
