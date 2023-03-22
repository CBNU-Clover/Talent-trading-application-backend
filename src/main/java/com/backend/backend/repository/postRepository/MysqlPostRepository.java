package com.backend.backend.repository.postRepository;

import com.backend.backend.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MysqlPostRepository implements PostRepository{
    
    private final EntityManager em; 
    @Override
    public Long save(Post post) {
        em.persist(post);
        return post.getId();
    }

    @Override
    public Post findPostById(Long id) {
        return em.find(Post.class,id);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = findPostById(id);
        em.remove(post);
    }
}
