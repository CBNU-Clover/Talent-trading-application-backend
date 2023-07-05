package com.backend.backend.common.scheduler;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.common.configuration.redis.RedisInitialization;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.popularPost.PopularPost;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.popularPostRepository.PopularPostRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class RedisViewCountSchedulerTest extends TestSetting {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private RedisInitialization redisInitialization;

    @Autowired
    private PostService postService;

    @Autowired
    private RedisViewCountScheduler redisViewCountScheduler;

    @Autowired
    private PopularPostRepository popularPostRepository;


    private Member member1;
    private Member member2;
    private Post post1;
    private Post post2;
    private Post post3;

    @BeforeEach
    void setUp() {
        member1 = Fixture.createMember("1");
        member2 = Fixture.createMember("2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        post1 = Fixture.createPost(member1, 1000L);
        post2 = Fixture.createPost(member2, 1000L);
        post3 = Fixture.createPost(member2, 1000L);
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
    }

    @Test
    void ValidPopularPostChange(){
        redisInitialization.init();

        //조회로 조회수 증가
        postService.readPost(post1.getId(), member1.getNickname().toString());
        postService.readPost(post1.getId(), member2.getNickname().toString());
        postService.readPost(post2.getId(), member1.getNickname().toString());

        //스케줄러의 기능을 강제로 실행
        redisViewCountScheduler.updateVisitorData();

        List<PopularPost> allPopularPosts = popularPostRepository.getAllPopularPosts();

        Assertions.assertThat(allPopularPosts.size()).isEqualTo(2);

        PopularPost popularPost = allPopularPosts.get(0);
        Assertions.assertThat(popularPost.getPost()).isSameAs(post1);

    }
}