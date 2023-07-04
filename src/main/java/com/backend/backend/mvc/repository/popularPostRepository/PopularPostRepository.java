package com.backend.backend.mvc.repository.popularPostRepository;

import com.backend.backend.mvc.domain.popularPost.PopularPost;

import java.util.List;

public interface PopularPostRepository {

    /**
     * PopularPost저장
     * @param popularPost 저장할 객체
     * @return 저장한 객체의 id
     */
    Long save(PopularPost popularPost);


    /**
     * id를 이용해서 PopularPost를 찾음
     * @param id 찾을 PopularPost의 id
     * @return 찾은 PopularPost 객체
     */
    PopularPost findPopularPostById(Long id);

    /**
     * 저장되어 있는 모든 인기게시글을 지움
     */
    void deleteAllPopularPosts();

    /**
     * 모든 인기게시글 반환
     * @return 인기게시글 리스트
     */
    List<PopularPost> getAllPopularPosts();

    /**
     *  저장된 인기게시글을 변경
     * @param popularPostList 변경할 인기게시글 들의 리스트
     */
    void changePopularPostList(List<PopularPost> popularPostList);
}
