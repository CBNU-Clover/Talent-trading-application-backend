package com.backend.backend.mvc.repository.postRepository;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.repository.postRepository.PostSearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;


class PostRepositoryTest extends TestSetting {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PostRepository postRepository;


    @Test

    void save() {
        Member member = Fixture.createMember("1");
        Long memberId = memberRepository.save(member);

        Post post = Fixture.createPost(memberRepository.findMemberById(memberId), 0L);

        Long postId = postRepository.save(post);

        Assertions.assertThat(postRepository.findPostById(postId)).isSameAs(post);


    }

    @Test
    void findPostById() {
        Member member = Fixture.createMember("1");
        Long memberId = memberRepository.save(member);

        Post post1 = Fixture.createPost(memberRepository.findMemberById(memberId), 0L);
        Long postId1 = postRepository.save(post1);
        Post post2 = Fixture.createPost(memberRepository.findMemberById(memberId), 0L);
        Long postId2 = postRepository.save(post2);

        Assertions.assertThat(postRepository.findPostById(postId2)).isSameAs(post2);
        Assertions.assertThat(postRepository.findPostById(postId1)).isSameAs(post1);
    }

    @Test
    void deletePostById() {
        Member member = Fixture.createMember("1");
        Long memberId = memberRepository.save(member);

        Post post1 = Fixture.createPost(memberRepository.findMemberById(memberId), 0L);
        Long postId1 = postRepository.save(post1);
        Post post2 = Fixture.createPost(memberRepository.findMemberById(memberId), 0L);
        Long postId2 = postRepository.save(post2);

        postRepository.deletePostById(postId1);
        assertThat(postRepository.findPostById(postId1)).isSameAs(null);
        org.junit.jupiter.api.Assertions.assertThrows(InvalidDataAccessApiUsageException.class, ()->{
            postRepository.deletePostById(postId1);
        });
    }

    @Test
    void findAll(){
        Member member = Fixture.createMember("1");
        Long memberId = memberRepository.save(member);

        Post post1 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .price(0L)
                .build();
        Long postId1 = postRepository.save(post1);
        Post post2 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .price(0L)
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
        Member member = Fixture.createMember("1");
        Long memberId = memberRepository.save(member);

        Post post1 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .price(0L)
                .build();
        Long postId1 = postRepository.save(post1);
        Post post2 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName(".")
                .content(",")
                .price(0L)
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
        Member member = Fixture.createMember("1");
        Long memberId = memberRepository.save(member);

        Post post1 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName("가낙닥 라")
                .content(",")
                .price(0L)
                .build();
        Long postId1 = postRepository.save(post1);
        Post post2 = Post.builder()
                .writer(memberRepository.findMemberById(memberId))
                .postName("가나다 라")
                .content(",")
                .price(0L)
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