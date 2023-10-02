package com.backend.backend.mvc.controller.member;

import com.backend.backend.Fixture;
import com.backend.backend.mvc.controller.member.memberdto.Check_Email;
import com.backend.backend.mvc.controller.member.memberdto.Check_nickname;
import com.backend.backend.mvc.controller.member.memberdto.MemberJoinRequest;
import com.backend.backend.mvc.controller.member.memberdto.MemberLoginRequest;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MemberRepository  memberRepository;

    @Autowired
    MemberService memberService;

    private Member member1;
    private Member member2;

    @BeforeEach
    void setup() {
        member1 = Fixture.createMember("1");
        memberRepository.save(member1);
        member2 = Fixture.createMember2();
    }

    @Test
    @DisplayName("nickname_check1")
    void nicknameCheck1() throws Exception {
        //given(memberService.check_Nickname(member.getNickname().toString())).willReturn(ResponseEntity.ok().body("중복되는 닉네임이 있습니다!!").getStatusCodeValue());
        Check_nickname check_nickname = new Check_nickname(member1.getNickname().toString());
        String json = new ObjectMapper().writeValueAsString(check_nickname);

        mockMvc.perform(post("/api/vi/members/check_nickname")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("중복되는 닉네임이 있습니다!!"))
                .andDo(print());
        //verify(memberService).check_Nickname(check_nickname.getNickname());
    }

    @Test
    @DisplayName("nickname_check2")
    void nicknameCheck2() throws Exception {
        Check_nickname check_nickname = new Check_nickname(member2.getNickname().toString());
        String json = new ObjectMapper().writeValueAsString(check_nickname);

        mockMvc.perform(post("/api/vi/members/check_nickname")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("중복되는 닉네임이 없습니다!!"))
                .andDo(print());
    }

    @Test
    @DisplayName("email_check1")
    void EmailCheck1() throws Exception {
        memberRepository.save(member1);
        Check_Email check_email = new Check_Email(member1.getEmail().toString());
        String json = new ObjectMapper().writeValueAsString(check_email);

        mockMvc.perform(post("/api/vi/members/check_email")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("중복되는 이메일이 있습니다!!"))
                .andDo(print());
    }

    @Test
    @DisplayName("email_check2")
    void EmailCheck2() throws Exception {
        Check_Email check_email = new Check_Email(member2.getEmail().toString());
        String json = new ObjectMapper().writeValueAsString(check_email);

        mockMvc.perform(post("/api/vi/members/check_email")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("중복되는 이메일이 없습니다!!"))
                .andDo(print());
    }

    @Test
    @DisplayName("join_success")
    void join() throws Exception {
        MemberJoinRequest memberJoinRequest = new MemberJoinRequest(
                "shinhyo",
                "shinhyo7632@naver.com",
                "신효민",
                "5555",
                "01055555555");
        String json = new ObjectMapper().writeValueAsString(memberJoinRequest);
        mockMvc.perform(post("/api/vi/members/join")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("회원가입이 성공했습니다!!"))
                .andDo(print());
    }

    @Test
    @DisplayName("login_success")
    void login() throws Exception {
        memberRepository.save(member2);
        String nickname= member2.getNickname().toString();
        String password=member2.getPassword().toString();
        MemberLoginRequest memberLoginRequest = new MemberLoginRequest(nickname,password);
        String json = new ObjectMapper().writeValueAsString(memberLoginRequest);
        mockMvc.perform(post("/api/vi/members/login")
                        .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(memberService.login(memberLoginRequest.getNickname(),memberLoginRequest.getPassWord())))
                .andDo(print());
    }
}
