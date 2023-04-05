package com.backend.backend.service;

import com.backend.backend.domain.Member;
import com.backend.backend.repository.memberRepository.MysqlMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MysqlMemberRepository mysqlMemberRepository;

    public void registerMember(Member member)
    {

        mysqlMemberRepository.save(member);
    }
}
