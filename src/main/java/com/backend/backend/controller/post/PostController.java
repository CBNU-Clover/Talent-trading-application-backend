package com.backend.backend.controller.post;

import com.backend.backend.DataProcessing.TokenParsing;
import com.backend.backend.controller.post.Dto.*;
import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.post.Post;
import com.backend.backend.repository.postRepository.PostSearch;
import com.backend.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/vi/boards")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;




    /**
     * Post를 새로 작성시 사용
     * @param postWriteRequest
     * @return
     */
    @PostMapping("/write")
    public ResponseEntity<Long> writePost(HttpServletRequest request, @RequestBody @Valid PostWriteRequest postWriteRequest){
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        postWriteRequest.setWriterNickname(result);
        System.out.println(result);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
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
    /**
     * 로그인한 유저가 자기 글 목록을 보고 싶은 경우 사용
     * @param request ( HttpServletRequest 객체 )
     * @return
     */

    @GetMapping("/getAllboard")
    public List<PostGetAllBoard> getAllboard(HttpServletRequest request)
    {
        List<PostGetAllBoard> ResultBoard=new ArrayList<>();
        List<Post> boardlist=new ArrayList<>();
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        boardlist=postService.getAllboard(result);

            for(int num=0 ; num<boardlist.size();num++)
            {
                ResultBoard.add(num,new PostGetAllBoard(boardlist.get(num).getId(),boardlist.get(num).getPostName(),boardlist.get(num).getContent()));
            }

        return ResultBoard;
    }

    @PostMapping("/deletePost")
    public void deletePost(@RequestBody @Valid PostDeleteBoard postDeleteBoard)
    {
        String id=postDeleteBoard.getDelete_id();
        postService.deletePost(Long.parseLong(id));
    }

    @PostMapping("/postsearch")
    public List<PostSearchBoard> searchPost(@RequestBody @Valid PostSearch postSearch)
    {
        List<PostSearchBoard> ResultBoard=new ArrayList<>();
        List<Post> searchboardlist=new ArrayList<>();
        searchboardlist=postService.searchPost(postSearch);

        for(int num=0 ; num<searchboardlist.size();num++)
        {
            ResultBoard.add(num,new PostSearchBoard(searchboardlist.get(num).getId(),searchboardlist.get(num).getWriter().getNickname(),searchboardlist.get(num).getPostName(),searchboardlist.get(num).getContent()));
        }

        return ResultBoard;
    }
}
