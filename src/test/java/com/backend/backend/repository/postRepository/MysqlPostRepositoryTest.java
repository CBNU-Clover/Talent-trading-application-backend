package com.backend.backend.repository.postRepository;

import com.backend.backend.domain.Member;
import com.backend.backend.domain.Post;
import com.backend.backend.repository.memberRepository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MysqlPostRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;


    @Test

    void save() {
        Member member = Member.builder()
                .name("1")
                .nickName("11")
                .passWord("ghj")
                .build();
        Long memberId = memberRepository.save(member);

        Post post = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .build();
        Long postId = postRepository.save(post);

        Assertions.assertThat(postRepository.findPostById(postId)).isSameAs(post);


    }

    @Test
    void findPostById() {
        Member member = Member.builder()
                .name("1")
                .nickName("11")
                .passWord("ghj")
                .build();
        Long memberId = memberRepository.save(member);

        Post post1 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .build();
        Long postId1 = postRepository.save(post1);
        Post post2 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .build();
        Long postId2 = postRepository.save(post2);

        Assertions.assertThat(postRepository.findPostById(postId2)).isSameAs(post2);
        Assertions.assertThat(postRepository.findPostById(postId1)).isSameAs(post1);
    }

    @Test
    void deletePostById() {
        Member member = Member.builder()
                .name("1")
                .nickName("11")
                .passWord("ghj")
                .build();
        Long memberId = memberRepository.save(member);

        Post post1 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .build();
        Long postId1 = postRepository.save(post1);
        Post post2 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .build();
        Long postId2 = postRepository.save(post2);

        postRepository.deletePostById(postId1);
        assertThat(postRepository.findPostById(postId1)).isSameAs(null);
        org.junit.jupiter.api.Assertions.assertThrows(InvalidDataAccessApiUsageException.class, ()->{
            postRepository.deletePostById(postId1);
        });
    }
}