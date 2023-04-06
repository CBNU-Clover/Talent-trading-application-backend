package com.backend.backend.controller.post;

import com.backend.backend.domain.Post;
import com.backend.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/write")
    public ResponseEntity<Long> writePost(@RequestBody @Valid PostWriteRequest postWriteRequest){

        Long postId = postService.writePost(postWriteRequest);

        return new ResponseEntity<>(postId,HttpStatus.CREATED);
    }

    @GetMapping("/read/{postId}")
    public ResponseEntity<PostReadResponse> readPost(@PathVariable("postId") Long postId){

        Post post = postService.readPost(postId);
        PostReadResponse postReadResponse = new PostReadResponse(post);

        return new ResponseEntity<>(postReadResponse,HttpStatus.OK);
    }
}
