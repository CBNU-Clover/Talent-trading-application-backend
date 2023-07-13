package com.backend.backend.mvc.controller.popularPost.dto;

import com.backend.backend.mvc.domain.popularPost.PopularPost;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PopularPostResponse {
    private String title;
    private Long price;
    private Long postId;

    public PopularPostResponse(PopularPost popularPost) {
        this.title = popularPost.getPost().getPostName().toString();
        this.price = popularPost.getPost().getPrice().getAmount();
        this.postId = popularPost.getPost().getId();
    }
}
