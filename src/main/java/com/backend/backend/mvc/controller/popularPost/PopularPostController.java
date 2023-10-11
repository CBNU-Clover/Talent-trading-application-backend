package com.backend.backend.mvc.controller.popularPost;

import com.backend.backend.mvc.controller.popularPost.dto.PopularPostResponse;
import com.backend.backend.mvc.controller.post.Dto.PostSearchBoard;
import com.backend.backend.mvc.controller.post.changedate.ChangeDate;
import com.backend.backend.mvc.domain.popularPost.PopularPost;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.popularPostRepository.PopularPostRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.repository.reviewRepository.ReviewRepository;
import com.backend.backend.mvc.service.PopularPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vi/popularPost")
@RequiredArgsConstructor
public class PopularPostController {

    private final PopularPostService popularPostService;
    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;
    private final PopularPostRepository popularPostRepository;


    @GetMapping("/all")
    public List<PopularPostResponse> getPopularPost()
    {
        return popularPostService.getAllPopularPosts();
    }

}
