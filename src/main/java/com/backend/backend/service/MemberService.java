package com.backend.backend.service;

import com.backend.backend.domain.member.Member;
import com.backend.backend.dto.memberdto.MemberJoinRequest;
import com.backend.backend.repository.memberRepository.MysqlMemberRepository;
import com.backend.backend.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    @Autowired
    private final MysqlMemberRepository mysqlMemberRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String key;
    private Long expireTimeMs=1000*60*60l; // 한시간
    public String join(MemberJoinRequest memberJoinRequest)
    {

        Member member=Member.builder()
                        .nickname(memberJoinRequest.getNickname())
                        .name(memberJoinRequest.getName())
                        .email(memberJoinRequest.getEmail())
                        .phoneNumber(memberJoinRequest.getPhoneNumber())
                        .passWord(encoder.encode(memberJoinRequest.getPassWord()))
                        .build();
        mysqlMemberRepository.save(member);
        
        return "Success";
    }

    public String login(String nickname,String passWord)
    {
        //membernickname없음
        Member selectedmember=mysqlMemberRepository.findMemberByNickname(nickname);
        // exception 필요

        //memberpassWord틀림
        if(!encoder.matches(passWord,selectedmember.getPassWord()))
        {
            return "로그인이 실패했습니다!!";
        }
        else {
            String token= JwtTokenUtil.createToken(selectedmember.getNickname(),key,expireTimeMs);

            return token;
        }

        //앞에서 Exception 안났으면 토큰 발행

    }

    public int check_Nickname(String nickname)
    {
        Boolean check;
        check=mysqlMemberRepository.nicknameDuplicateCheck(nickname);
        if(check)
        {

            return 1;
        }
        else
        {
            return 0;
        }
    }
    public int check_Email(String email)
    {
        Boolean check;
        check=mysqlMemberRepository.emailDuplicateCheck(email);
        if(check)
        {

            return 1;
        }
        else
        {
            return 0;
        }
    }
}
