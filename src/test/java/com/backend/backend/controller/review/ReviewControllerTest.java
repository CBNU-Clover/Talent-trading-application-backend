package com.backend.backend.controller.review;

import com.backend.backend.domain.Member;
import com.backend.backend.domain.Post;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.postRepository.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.criterion.NotNullExpression;
import org.junit.jupiter.api.Assertions;
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
class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReviewController reviewController;

    ObjectMapper objectMapper = new ObjectMapper();

    private String writerNickname="74981519";
    private Long postId;

    @BeforeEach
    void setup(){
        memberRepository.save(Member.builder()
                .name("415646556456")
                .nickName(writerNickname)
                .passWord("5456")
                .email("566511561sd1")
                .build()
        );

        postId = postRepository.save(Post.builder()
                .writer(memberRepository.findMemberByNickname(writerNickname))
                .postName("49849849849848")
                .content("51919519598")
                .build()
        );


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
}