package com.backend.backend.repository.postRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.post.Post;
import com.backend.backend.repository.memberRepository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;


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
                .nickname("11")
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
                .nickname("11")
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
                .nickname("115465165")
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

    @Test
    void findAll(){
        Member member = Member.builder()
                .name("1")
                .nickname("54654949898198")
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

        PostSearch postSearch = PostSearch.builder()
                .postName(".")
                .build();
        List<Post> posts = postRepository.searchPost(postSearch);
        Assertions.assertThat(posts).contains(post1, post2);
    }

    @Test
    void findAllOrder(){
        Member member = Member.builder()
                .name("1")
                .nickname("419651965819198")
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

        PostSearch postSearch = PostSearch.builder()
                .postName(".")
                .build();
        List<Post> posts = postRepository.searchPost(postSearch);
        Assertions.assertThat(posts.get(0)).isSameAs(post1);
        Assertions.assertThat(posts.get(1)).isSameAs(post2);
    }

    @Test
    void searchPost(){
        Member member = Member.builder()
                .name("1")
                .nickname("54654949898198")
                .passWord("ghj")
                .build();
        Long memberId = memberRepository.save(member);

        Post post1 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName("가낙닥 라")
                .content(",")
                .build();
        Long postId1 = postRepository.save(post1);
        Post post2 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName("가나다 라")
                .content(",")
                .build();
        Long postId2 = postRepository.save(post2);


        PostSearch postSearch1 = PostSearch.builder()
                .postName("가")
                .build();
        List<Post> posts1 = postRepository.searchPost(postSearch1);
        Assertions.assertThat(posts1.size()).isEqualTo(2);
        Assertions.assertThat(posts1).contains(post1, post2);

        PostSearch postSearch2 = PostSearch.builder()
                .postName("가나다")
                .build();
        List<Post> posts2 = postRepository.searchPost(postSearch2);
        Assertions.assertThat(posts2.size()).isEqualTo(1);
        Assertions.assertThat(posts2.get(0)).isSameAs(post2);

    }
}