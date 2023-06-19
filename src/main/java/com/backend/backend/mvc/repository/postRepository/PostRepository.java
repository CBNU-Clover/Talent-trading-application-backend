package com.backend.backend.mvc.repository.postRepository;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;

import java.util.List;

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

    /**
     * PostSearch의 조건에 맞는 Post반환
     * @param postSearch
     * @return
     */
    List<Post> searchPost(PostSearch postSearch);

    /**
     * 해당 member가 작성한 Post를 전부 반환
     * @param member 작성자
     * @return
     */
    List<Post> findPostsByMember(Member member);
}
