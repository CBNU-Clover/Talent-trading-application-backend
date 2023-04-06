package com.backend.backend.service;

import com.backend.backend.jwt.JwtToken;
import com.backend.backend.jwt.JwtTokenProvider;
import com.backend.backend.repository.memberRepository.MysqlMemberRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    private final BCryptPasswordEncoder encoder;
    private final MysqlMemberRepository mysqlMemberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(BCryptPasswordEncoder encoder ,MysqlMemberRepository mysqlMemberRepository ,AuthenticationManagerBuilder authenticationManagerBuilder , JwtTokenProvider jwtTokenProvider)
    {
        this.encoder=encoder;
        this.mysqlMemberRepository=mysqlMemberRepository;
        this.authenticationManagerBuilder=authenticationManagerBuilder;
        this.jwtTokenProvider=jwtTokenProvider;
    }
    public JwtToken login(String email, String password)
    {
        //Authentication 객체 생성
       UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(email,password);
       Authentication authentication=authenticationManagerBuilder.getObject().authenticate(authenticationToken);
       //검증된 인증 정보로 JWT 토큰 생성
       JwtToken token=jwtTokenProvider.generateToken(authentication);
       return token;
    }
}