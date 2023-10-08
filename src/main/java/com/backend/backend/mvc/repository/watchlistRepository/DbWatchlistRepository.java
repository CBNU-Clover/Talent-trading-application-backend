package com.backend.backend.mvc.repository.watchlistRepository;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.watchlist.Watchlist;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.mvc.domain.watchlist.QWatchlist.watchlist;

@Repository
public class DbWatchlistRepository implements WatchlistRepository{

    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbWatchlistRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public Long save(Watchlist watchlist) {
        em.persist(watchlist);
        return watchlist.getId();
    }

    @Override
    public Watchlist findWatchlistById(Long id) {
        return em.find(Watchlist.class, id);
    }

    @Override
    public Long getWatchlistCount(Post post) {
        return query
                .select(watchlist.count())
                .from(watchlist)
                .where(watchlist.post.eq(post))
                .fetchFirst();
    }

    @Override
    public List<Post> findWatchlistByMember(Member member) {
        return query
                .select(watchlist.post)
                .from(watchlist)
                .where(watchlist.member.eq(member))
                .fetch();
    }
}
