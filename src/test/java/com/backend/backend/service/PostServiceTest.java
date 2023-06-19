package com.backend.backend.service;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;



class PostServiceTest extends TestSetting {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

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
    /**
    @Test
    void deletePost() {
        Member member = Member.builder()
                .name("1")
                .nickname("111")
                .passWord("ghj")
                .build();
        memberRepository.save(member);

        org.junit.jupiter.api.Assertions.assertThrows(PermissionDeniedException.class, ()->{
            postService.deletePost(initPostId1);
        });
    }
    */
}