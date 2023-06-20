package com.backend.backend.controller.review;

import com.backend.backend.Fixture;
import com.backend.backend.mvc.controller.review.dto.ReviewWriteRequest;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.review.Review;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.repository.reviewRepository.ReviewRepository;
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


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private String writerNickname;
    private Long postId;

    @BeforeEach
    void setup(){
        Member member = Fixture.createMember("1");
        memberRepository.save(member);
        writerNickname = member.getNickname().toString();

        postId = postRepository.save(Fixture.createPost(member,0L));


    }

    @Test
    void writeReview() throws Exception {
        ReviewWriteRequest reviewWriteRequest = new ReviewWriteRequest(postId, writerNickname, "4546500");
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .post("/review/write")
                .content(objectMapper.writeValueAsString(reviewWriteRequest))
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void writeReviewError() throws Exception {
        ReviewWriteRequest reviewWriteRequest = new ReviewWriteRequest(null, writerNickname, "4546500");

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .post("/review/write")
                .content(objectMapper.writeValueAsString(reviewWriteRequest))
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    void readReview() throws Exception {
        String content="4546545";
        Review review = Review.builder()
                .post(postRepository.findPostById(postId))
                .writer(memberRepository.findMemberByNickname(writerNickname))
                .content(content)
                .build();
        Long reviewId = reviewRepository.save(review);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/review/read/"+reviewId.toString())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("writerNickname",writerNickname).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("postId",postId).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("content",content).exists());
    }
}