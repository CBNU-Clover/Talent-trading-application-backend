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
                        .nickName(memberJoinRequest.getNickName())
                        .name(memberJoinRequest.getName())
                        .email(memberJoinRequest.getEmail())
                        .phoneNumber(memberJoinRequest.getPhoneNumber())
                        .passWord(encoder.encode(memberJoinRequest.getPassWord()))
                        .build();
        mysqlMemberRepository.save(member);
        
        return "Success";
    }

    public String login(String nickName,String passWord)
    {
        //membernickName없음
        Member selectedmember=mysqlMemberRepository.findMemberByNickname(nickName);
        // exception 필요

        //memberpassWord틀림
        if(!encoder.matches(passWord,selectedmember.getPassWord()))
        {

        }
        //앞에서 Exception 안났으면 토큰 발행
        String token= JwtTokenUtil.createToken(selectedmember.getNickName(),key,expireTimeMs);

        return token;
    }

    public int check_Nickname(String nickName)
    {
        Boolean check;
        check=mysqlMemberRepository.nicknameDuplicateCheck(nickName);
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
