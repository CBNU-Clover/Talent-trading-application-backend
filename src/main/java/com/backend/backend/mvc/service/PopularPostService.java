package com.backend.backend.mvc.service;

import com.backend.backend.mvc.controller.popularPost.dto.PopularPostResponse;
import com.backend.backend.mvc.controller.post.changedate.ChangeDate;
import com.backend.backend.mvc.domain.popularPost.PopularPost;
import com.backend.backend.mvc.repository.popularPostRepository.PopularPostRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.repository.reviewRepository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PopularPostService {

    private final PopularPostRepository popularPostRepository;
    private final ReviewRepository reviewRepository;
    private final PostRepository postRepository;
    public List<PopularPostResponse> getAllPopularPosts(){
        List<PopularPostResponse> ResultBoard=new ArrayList<>();
        List<PopularPost> popularPostList;
        popularPostList=popularPostRepository.getAllPopularPosts();

        for(int num=0 ; num<popularPostList.size();num++)
        {

            ResultBoard.add(num,new PopularPostResponse(popularPostList.get(num).getPost().getId(),
                    popularPostList.get(num).getPost().getWriter().getNickname().toString(),
                    popularPostList.get(num).getPost().getPostName().toString(),popularPostList.get(num).getPost().getContent().toString(),
                    popularPostList.get(num).getPost().getPrice().getAmount(),
                    ChangeDate.formatTimeString(popularPostList.get(num).getPost().getDate()),(long)reviewRepository.findReviewsByPost(postRepository.findPostById(popularPostList.get(num).getPost().getId())).size(),
                    popularPostList.get(num).getPost().getImage().getId().toString()));

        }

        return ResultBoard;
    }




}
