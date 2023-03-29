package com.backend.backend.repository.postRepository;

import com.backend.backend.domain.Post;
import com.backend.backend.domain.QPost;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.domain.QPost.post;

@Repository
public class MysqlPostRepository implements PostRepository{
    
    private final EntityManager em;
    private final JPAQueryFactory query;

    public MysqlPostRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

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

    @Override
    public List<Post> findAll(PostSearch postSearch) {
        return query
                .select(post)
                .from(post)
                .where(postNameLike(postSearch.getPostName()))
                .limit(100)
                .fetch();
    }

    private BooleanExpression postNameLike(String name){
        if(!StringUtils.hasText(name)){
            return null;
        }
        return post.postName.like(name);
    }
}
