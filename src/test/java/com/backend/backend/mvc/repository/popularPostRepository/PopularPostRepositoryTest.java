package com.backend.backend.mvc.repository.popularPostRepository;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.popularPost.PopularPost;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PopularPostRepositoryTest extends TestSetting {

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

    @Test
    void duplicateSaveError(){
        PopularPost popularPost1 = new PopularPost(post1, 10L);
        PopularPost popularPost2 = new PopularPost(post1, 20L);
        popularPostRepository.save(popularPost1);


//        org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
//            popularPostRepository.save(popularPost2);
//        });
    }

    @Test
    void deleteAllPopularPosts() {
        PopularPost popularPost1 = new PopularPost(post1, 10L);
        PopularPost popularPost2 = new PopularPost(post2, 20L);
        popularPostRepository.save(popularPost1);
        popularPostRepository.save(popularPost2);


        popularPostRepository.deleteAllPopularPosts();

        Assertions.assertThat(popularPostRepository.findPopularPostById(popularPost1.getId())).isNull();
        Assertions.assertThat(popularPostRepository.findPopularPostById(popularPost2.getId())).isNull();
    }

    @Test
    void getAllPopularPosts() {
        PopularPost popularPost1 = new PopularPost(post1, 10L);
        PopularPost popularPost2 = new PopularPost(post2, 20L);
        popularPostRepository.save(popularPost1);
        popularPostRepository.save(popularPost2);

        List<PopularPost> allPopularPosts = popularPostRepository.getAllPopularPosts();
        Assertions.assertThat(allPopularPosts).contains(popularPost1, popularPost2);
    }

    @Test
    void changePopularPostList() {
        Post post3 = Fixture.createPost(member, 2000L);
        Post post4 = Fixture.createPost(member, 5000L);
        postRepository.save(post3);
        postRepository.save(post4);

        PopularPost popularPost1 = new PopularPost(post1, 10L);
        PopularPost popularPost2 = new PopularPost(post2, 20L);
        popularPostRepository.save(popularPost1);
        popularPostRepository.save(popularPost2);

        PopularPost changePopularPost1 = new PopularPost(post3, 10L);
        PopularPost changePopularPost2 = new PopularPost(post4, 55L);

        popularPostRepository.changePopularPostList(Arrays.asList(changePopularPost1,changePopularPost2));

        List<PopularPost> allPopularPosts = popularPostRepository.getAllPopularPosts();
        Assertions.assertThat(allPopularPosts).contains(changePopularPost1, changePopularPost2);
        Assertions.assertThat(allPopularPosts).doesNotContain(popularPost1, popularPost2);

    }
}