package com.backend.backend.mvc.controller.popularPost;

import com.backend.backend.Fixture;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.popularPost.PopularPost;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.popularPostRepository.PopularPostRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.repository.reviewRepository.ReviewRepository;
import com.backend.backend.mvc.service.PopularPostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class PopularPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PopularPostRepository popularPostRepository;

    @Autowired
    private PopularPostService popularPostService;

    private Member member;
    private Post post1;
    private Post post2;

    private PopularPost popularPost1;
    private PopularPost popularPost2;

    @BeforeEach
    void setUp() {
        member = Fixture.createMember("1");
        memberRepository.save(member);
        post1 = Fixture.createPost(member, 1000L);
        postRepository.save(post1);
        post2 = Fixture.createPost(member, 2000L);
        postRepository.save(post2);
        popularPost1 = new PopularPost(post1, 10L);
        popularPost2 = new PopularPost(post2, 20L);
        popularPostRepository.save(popularPost1);
        popularPostRepository.save(popularPost2);
    }
    @Test
    void readAllPopularPost() throws Exception {
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/popularPost/all")
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("popularPostList.[0].postId",
                        popularPost1.getPost().getId()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("popularPostList.[0].price",
                        popularPost1.getPost().getPrice().getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("popularPostList.[0].title",
                        popularPost1.getPost().getPostName().toString()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("popularPostList.[1].postId",
                        popularPost2.getPost().getId()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("popularPostList.[1].price",
                        popularPost2.getPost().getPrice().getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("popularPostList.[1].title",
                        popularPost2.getPost().getPostName().toString()).exists());
    }
}