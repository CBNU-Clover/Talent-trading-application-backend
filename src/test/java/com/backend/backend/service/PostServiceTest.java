package com.backend.backend.service;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.post.Post;
import com.backend.backend.exception.PermissionDeniedException;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.postRepository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostServiceTest {


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
        Member member = Member.builder()
                .name("1")
                .nickname("11")
                .passWord("ghj")
                .build();
        initMemberId = memberRepository.save(member);

        Post post1 = Post.builder()
                .writer(memberRepository.findMemberById(initMemberId))
                .postName(".")
                .content(",")
                .build();

        initPostId1=postRepository.save(post1);


        Post post2 = Post.builder()
                .writer(memberRepository.findMemberById(initMemberId))
                .postName(".")
                .content(",")
                .build();
        initPostId2=postRepository.save(post2);
    }

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
}