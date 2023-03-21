package com.backend.backend.repository.postRepository;

import com.backend.backend.domain.Member;
import com.backend.backend.domain.Post;

public interface PostRepository {
    /**
     * Post객체 전달시 레파지토리에 저장됨
     * @param post
     * @return
     */
    Long save(Post post);

    /**
     * id를 이용해서 Post객체 검색
     * @param id
     * @return
     */
    Post findPostById(Long id);

    /**
     * id를 이용해서 Post 객체 삭제
     * @param id
     */
    void deletePostById(Long id);
}
