package com.backend.backend.controller.post;

import com.backend.backend.controller.post.Dto.PostModifyRequest;
import com.backend.backend.controller.post.Dto.PostReadResponse;
import com.backend.backend.controller.post.Dto.PostWriteRequest;
import com.backend.backend.domain.post.Post;
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

    /**
     * Post를 새로 작성시 사용
     * @param postWriteRequest
     * @return
     */
    @PostMapping("/write")
    public ResponseEntity<Long> writePost(@RequestBody @Valid PostWriteRequest postWriteRequest){

        Long postId = postService.writePost(postWriteRequest);

        return new ResponseEntity<>(postId,HttpStatus.CREATED);
    }

    /**
     * Post를 읽어올경우 사용
     * @param postId
     * @return
     */
    @GetMapping("/read/{postId}")
    public ResponseEntity<PostReadResponse> readPost(@PathVariable("postId") Long postId){

        Post post = postService.readPost(postId);
        PostReadResponse postReadResponse = new PostReadResponse(post);

        return new ResponseEntity<>(postReadResponse,HttpStatus.OK);
    }

    /**
     * Post를 수정할경우 사용
     * @param postModifyRequest
     * @return
     */
    @PatchMapping("/modify")
    public ResponseEntity<Long> modifyPost(@RequestBody @Valid PostModifyRequest postModifyRequest){

        Long postId = postService.modifyPost(postModifyRequest);

        return new ResponseEntity<>(postId,HttpStatus.OK);
    }
}
