package com.backend.backend.service;

import com.backend.backend.domain.Post;
import com.backend.backend.repository.postRepository.PostRepository;
import com.backend.backend.repository.postRepository.PostSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    /**
     * post객체 전달시 저장
     * @param post
     * @return
     */
    public Long writePost(Post post){
        return postRepository.save(post);
    }

    /**
     * post를 식별 가능한 방법이 id 뿐이기에 id로 검색하여 Post반환
     * @param id
     * @return
     */
    public Post readPost(Long id){
        return postRepository.findPostById(id);
    }

    /**
     * 조건에 맞는 모든 post를 찾아서 반환
     * @param postSearch
     * @return
     */
    public List<Post> searchPost(PostSearch postSearch){
        return postRepository.findAll(postSearch);
    }

    /**
     * post를 식별가능한 방법이 id 뿐이기에 id로 삭제
     * @param id
     */
    public void deletePost(Long id){
        postRepository.deletePostById(id);
    }


}
