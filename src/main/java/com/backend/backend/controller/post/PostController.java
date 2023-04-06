package com.backend.backend.controller.post;

import com.backend.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/write")
    public ResponseEntity<Void> postWrite(@RequestBody @Valid PostWriteRequest postWriteRequest){

        postService.writePost(postWriteRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}
