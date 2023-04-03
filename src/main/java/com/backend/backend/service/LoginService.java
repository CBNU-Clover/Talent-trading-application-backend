package com.backend.backend.service;

import com.backend.backend.domain.Member;
import com.backend.backend.dto.LoginDTO;
import com.backend.backend.repository.memberRepository.MysqlMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private MysqlMemberRepository mysqlMemberRepository;

    public void login(LoginDTO loginDTO)
    {

    }


}
