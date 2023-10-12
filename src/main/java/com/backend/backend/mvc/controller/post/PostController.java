package com.backend.backend.mvc.controller.post;

import com.backend.backend.common.DataProcessing.TokenParsing;
import com.backend.backend.mvc.controller.post.Dto.*;
import com.backend.backend.mvc.controller.post.changedate.ChangeDate;
import com.backend.backend.mvc.domain.image.Image;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.imageRepository.ImageRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.repository.postRepository.PostSearch;
import com.backend.backend.mvc.repository.reviewRepository.ReviewRepository;
import com.backend.backend.mvc.service.PostService;
import com.backend.backend.mvc.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vi/boards")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final PostRepository postRepository;
    private final ReviewRepository reviewRepository;
    private final ImageRepository imageRepository;



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
        Long postId = postService.writePost(postWriteRequest);
        return new ResponseEntity<>(postId,HttpStatus.CREATED);
    }

    /**
     * Post를 읽어올경우 사용
     * @param postId
     * @return
     */
    @GetMapping("/read/{postId}")
    public ResponseEntity<PostReadResponse> readPost(@PathVariable("postId") Long postId,HttpServletRequest request){
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        Post post = postService.readPost(postId,result);
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
                ResultBoard.add(num,new PostGetAllBoard(boardlist.get(num).getId(),
                        boardlist.get(num).getPostName().toString(),boardlist.get(num).getContent().toString(),
                        boardlist.get(num).getPrice().getAmount(),ChangeDate.formatTimeString(boardlist.get(num).getDate()),result,(long)reviewRepository.findReviewsByPost(postRepository.findPostById(boardlist.get(num).getId())).size()));
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
        List<Post> searchboardlist;
        searchboardlist=postService.searchPost(postSearch);



        for(int num=0 ; num<searchboardlist.size();num++)
        {

            /*ResultBoard.add(num,new PostSearchBoard(searchboardlist.get(num).getId(),
                    searchboardlist.get(num).getWriter().getNickname().toString(),
                    searchboardlist.get(num).getPostName().toString(),searchboardlist.get(num).getContent().toString(),
                    searchboardlist.get(num).getPrice().getAmount(),
                    ChangeDate.formatTimeString(searchboardlist.get(num).getDate()),(long)reviewRepository.findReviewsByPost(postRepository.findPostById(searchboardlist.get(num).getId())).size()
                    ,searchboardlist.get(num).getImage().getImage()));*/
            ResultBoard.add(num,new PostSearchBoard(searchboardlist.get(num).getId(),
                    searchboardlist.get(num).getWriter().getNickname().toString(),
                    searchboardlist.get(num).getPostName().toString(),searchboardlist.get(num).getContent().toString(),
                    searchboardlist.get(num).getPrice().getAmount(),
                    ChangeDate.formatTimeString(searchboardlist.get(num).getDate()),(long)reviewRepository.findReviewsByPost(postRepository.findPostById(searchboardlist.get(num).getId())).size()
                    ));

        }

        return ResultBoard;
    }

}
