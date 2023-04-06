package com.backend.backend.controller.post;

import com.backend.backend.controller.post.Dto.PostWriteRequest;
import com.backend.backend.domain.Member;
import com.backend.backend.domain.Post;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setup(){
        memberRepository.save(Member.builder()
                .name("415646556456")
                .nickName("74981519")
                .passWord("5456")
                .email("566511561sd1")
                .build()
        );

    }

    ObjectMapper objectMapper = new ObjectMapper();



    @Test
    void writePost() throws Exception {
        PostWriteRequest postWriteRequest = new PostWriteRequest("74981519","456","45616");
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .post("/post/write")
                .content(objectMapper.writeValueAsString(postWriteRequest))
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void readPost() {
    }

    @Test
    void modifyPost() {
    }
}