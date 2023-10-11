package com.backend.backend.mvc.service;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.popularPost.PopularPost;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.popularPostRepository.PopularPostRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PopularPostServiceTest extends TestSetting {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PopularPostRepository popularPostRepository;

    private Member member;
    private Post post1;
    private Post post2;

    @BeforeEach
    void setUp() {
        member = Fixture.createMember("1");
        memberRepository.save(member);
        post1 = Fixture.createPost(member, 1000L);
        postRepository.save(post1);
        post2 = Fixture.createPost(member, 2000L);
        postRepository.save(post2);
    }


}