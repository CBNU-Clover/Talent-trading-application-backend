package com.backend.backend.mvc.service;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.common.configuration.redis.RedisKey;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.service.PostService;
import com.backend.backend.common.configuration.redis.RedisInitialization;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import static org.mockito.ArgumentMatchers.any;



class PostServiceTest extends TestSetting {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Autowired
    RedisInitialization redisInitialization;

    @Autowired
    RedisTemplate<String ,String> redisTemplate;

    Long initMemberId;
    Long initPostId1;
    Long initPostId2;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
        Member member = Fixture.createMember("1");
        initMemberId = memberRepository.save(member);

        Post post1 = Fixture.createPost(memberRepository.findMemberById(initMemberId), 0L);

        initPostId1=postRepository.save(post1);


        Post post2 = Fixture.createPost(memberRepository.findMemberById(initMemberId), 0L);
        initPostId2=postRepository.save(post2);
    }
    @Test
    void addViewCountValidTest(){
        Member member = memberRepository.findMemberById(initMemberId);
        Post post = postRepository.findPostById(initPostId1);

        postService.readPost(post.getId());
        postService.readPost(post.getId());

        Assertions.assertThat(post.getViewCount().getCount()).isEqualTo(2L);
    }

    @Test
    void addViewCountValidWithoutDuplicateTest(){
        redisInitialization.init();

        Member member = memberRepository.findMemberById(initMemberId);
        Member member2 = Fixture.createMember("2");
        memberRepository.save(member2);
        Post post = postRepository.findPostById(initPostId1);

        postService.readPost(post.getId(),member.getNickname().toString());
        postService.readPost(post.getId(),member.getNickname().toString());
        postService.readPost(post.getId(),member2.getNickname().toString());
        postService.readPost(post.getId(),member2.getNickname().toString());

        Long count = post.getViewCount().getCount() +
                Long.parseLong(
                        redisTemplate.opsForValue().get(RedisKey.PostViewCount + "_" + post.getId().toString())
                );
        Assertions.assertThat(count).isEqualTo(2L);
    }
}