package com.backend.backend.mvc.controller.post.Dto;

import com.backend.backend.mvc.controller.post.changedate.ChangeDate;
import com.backend.backend.mvc.domain.post.Post;
import lombok.Data;

@Data
public class PostReadResponse {
    private Long postId;
    private String writerNickname;
    private String postName;

    private String content;
    private Long price;

    private String date;
    private String image_url;


    public PostReadResponse(Post post) {
        this.postId=post.getId();
        this.writerNickname = post.getWriter().getNickname().toString();
        this.postName = post.getPostName().toString();
        this.content = post.getContent().toString();
        this.price=post.getPrice().getAmount();
        this.date= ChangeDate.formatTimeString(post.getDate());
        this.image_url=post.getImage().getId().toString();
    }
}
