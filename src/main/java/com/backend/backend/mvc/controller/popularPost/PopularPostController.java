package com.backend.backend.mvc.controller.popularPost;

import com.backend.backend.mvc.controller.popularPost.dto.PopularPostListResponse;
import com.backend.backend.mvc.controller.post.Dto.PostReadResponse;
import com.backend.backend.mvc.service.PopularPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/popularPost")
@RequiredArgsConstructor
public class PopularPostController {

    private final PopularPostService postService;

    @GetMapping("/all")
    public ResponseEntity<PopularPostListResponse> readAllPopularPost(){
        PopularPostListResponse popularPostListResponse = new PopularPostListResponse(postService.getAllPopularPosts());
        return new ResponseEntity<>(popularPostListResponse, HttpStatus.OK);
    }
}
