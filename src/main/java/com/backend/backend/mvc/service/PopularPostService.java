package com.backend.backend.mvc.service;

import com.backend.backend.mvc.domain.popularPost.PopularPost;
import com.backend.backend.mvc.repository.popularPostRepository.PopularPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PopularPostService {

    private final PopularPostRepository popularPostRepository;

    public List<PopularPost> getAllPopularPosts(){
        return popularPostRepository.getAllPopularPosts();
    }
}
