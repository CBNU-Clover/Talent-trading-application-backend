package com.backend.backend.controller.post;

import com.backend.backend.controller.post.Dto.PostModifyRequest;
import com.backend.backend.controller.post.Dto.PostReadResponse;
import com.backend.backend.controller.post.Dto.PostWriteRequest;
import com.backend.backend.domain.Member;
import com.backend.backend.domain.Post;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.postRepository.PostRepository;
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

    @Autowired
    private PostRepository postRepository;

    private String writerNickname="74981519";

    @BeforeEach
    void setup(){
        memberRepository.save(Member.builder()
                .name("415646556456")
                .nickName(writerNickname)
                .passWord("5456")
                .email("566511561sd1")
                .build()
        );

    }

    ObjectMapper objectMapper = new ObjectMapper();



    @Test
    void writePost() throws Exception {
        PostWriteRequest postWriteRequest = new PostWriteRequest(writerNickname,"456","45616");
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .post("/post/write")
                .content(objectMapper.writeValueAsString(postWriteRequest))
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void readPost() throws Exception {
        String postName="123";
        String postContent="123";
        Post post = Post.builder()
                .writer(memberRepository.findMemberByNickname(writerNickname))
                .postName(postName)
                .content(postContent)
                .build();
        Long postId = postRepository.save(post);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/post/read/"+postId.toString())
                .contentType(MediaType.APPLICATION_JSON)
        );
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("writerNickname",writerNickname).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("postName",postName).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("content",postContent).exists());

    }

    @Test
    void modifyPost() throws Exception {
        String postName="123";
        String postContent="123";
        Post post = Post.builder()
                .writer(memberRepository.findMemberByNickname(writerNickname))
                .postName(postName)
                .content(postContent)
                .build();
        Long postId = postRepository.save(post);

        PostModifyRequest postModifyRequest = new PostModifyRequest(postId, writerNickname, "789", "81815");

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .patch("/post/modify")
                .content(objectMapper.writeValueAsString(postModifyRequest))
                .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}