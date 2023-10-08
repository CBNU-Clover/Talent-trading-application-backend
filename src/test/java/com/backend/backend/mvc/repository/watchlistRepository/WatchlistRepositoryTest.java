package com.backend.backend.mvc.repository.watchlistRepository;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.watchlist.Watchlist;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WatchlistRepositoryTest extends TestSetting {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WatchlistRepository watchlistRepository;



    @Test
    void findWatchlistById() {
        Member member1 = Fixture.createMember("1");
        Member member2 = Fixture.createMember("2");
        Post post = Fixture.createPost(member1, 1000L);
        memberRepository.save(member1);
        memberRepository.save(member2);
        postRepository.save(post);

        Watchlist watchlist = new Watchlist(member2, post);
        Long id = watchlistRepository.save(watchlist);

        Watchlist foundWatchlist = watchlistRepository.findWatchlistById(id);

        Assertions.assertThat(foundWatchlist).isSameAs(watchlist);
    }

    @Test
    void getWatchlistCount() {
        Member member1 = Fixture.createMember("1");
        Member member2 = Fixture.createMember("2");
        Member member3 = Fixture.createMember("3");
        Post post = Fixture.createPost(member1, 1000L);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        postRepository.save(post);

        Watchlist watchlist1 = new Watchlist(member2, post);
        watchlistRepository.save(watchlist1);
        Watchlist watchlist2 = new Watchlist(member3, post);
        watchlistRepository.save(watchlist2);

        Long watchlistCount = watchlistRepository.getWatchlistCount(post);

        Assertions.assertThat(watchlistCount).isEqualTo(2);
    }

    @Test
    void findWatchlistByMember() {
        Member member1 = Fixture.createMember("1");
        Member member2 = Fixture.createMember("2");
        Member member3 = Fixture.createMember("3");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        Post post1 = Fixture.createPost(member1, 1000L);
        Post post2 = Fixture.createPost(member2, 1000L);
        postRepository.save(post1);
        postRepository.save(post2);

        Watchlist watchlist1 = new Watchlist(member3, post1);
        watchlistRepository.save(watchlist1);
        Watchlist watchlist2 = new Watchlist(member3, post2);
        watchlistRepository.save(watchlist2);

        List<Post> result = watchlistRepository.findWatchlistByMember(member3);

        Assertions.assertThat(result).contains(post1, post2);


    }
}