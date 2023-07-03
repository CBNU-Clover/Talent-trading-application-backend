package com.backend.backend.controller.point;

import com.backend.backend.Fixture;
import com.backend.backend.common.DataProcessing.TokenParsing;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.pointDetailRepository.PointDetailRepository;
import com.backend.backend.mvc.service.MemberService;
import com.backend.backend.mvc.service.PointService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class PointControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    PointService pointService;

    @Autowired
    PointDetailRepository pointDetailRepository;

    @Mock
    HttpServletRequest mockRequest;

    private Member member2;
    private String token;
    private final String AUTHORIZATION_HEADER = "Authorization";
    private String result;

    @BeforeEach
    void setup()
    {
        member2= Fixture.createMember2();
        memberRepository.save(member2);
        token=memberService.login(String.valueOf(member2.getNickname()),String.valueOf(member2.getPassword()));
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization",token);
        TokenParsing tokenParsing=new TokenParsing();
        result= tokenParsing.ExtractNickname(request);
    }

    @Test
    @DisplayName("show_point")
    void show_point()
    {
        System.out.println(result);
    }


}
