package com.backend.backend.mvc.repository.popularPostRepository;

import com.backend.backend.mvc.domain.popularPost.PopularPost;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.mvc.domain.popularPost.QPopularPost.popularPost;

@Repository
public class DbPopularPostRepository implements PopularPostRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbPopularPostRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Long save(PopularPost popularPost) {
        em.persist(popularPost);
        return popularPost.getId();
    }

    @Override
    public PopularPost findPopularPostById(Long id) {
        return em.find(PopularPost.class, id);
    }

    @Override
    public void deleteAllPopularPosts() {
        query.delete(popularPost).execute();
        em.clear();
    }

    @Override
    public List<PopularPost> getAllPopularPosts() {
        return query
                .select(popularPost)
                .from(popularPost)
                .orderBy(popularPost.viewCount.desc())
                .fetch();
    }

    @Override
    public void changePopularPostList(List<PopularPost> popularPostList) {
        deleteAllPopularPosts();
        for(PopularPost popular: popularPostList){
            save(popular);
        }
    }
}
