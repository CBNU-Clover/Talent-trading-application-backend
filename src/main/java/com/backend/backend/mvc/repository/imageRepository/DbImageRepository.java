package com.backend.backend.mvc.repository.imageRepository;

import com.backend.backend.mvc.domain.image.Image;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class DbImageRepository implements ImageRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbImageRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }
    @Override
    public Long save(Image image) {
        em.persist(image);
        return image.getId();
    }

    @Override
    public Image findImageById(Long id) {
        return em.find(Image.class, id);
    }
}
