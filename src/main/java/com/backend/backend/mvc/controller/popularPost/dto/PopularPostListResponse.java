package com.backend.backend.mvc.controller.popularPost.dto;

import com.backend.backend.mvc.domain.popularPost.PopularPost;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class PopularPostListResponse {
    private List<PopularPostResponse> popularPostList;

    public PopularPostListResponse(List<PopularPost> popularPostList) {
        this.popularPostList = popularPostList.stream().map(PopularPostResponse::new).toList();
    }
}
