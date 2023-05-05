package com.backend.backend.service;

import com.backend.backend.domain.member.Member;
import com.backend.backend.repository.memberRepository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PointServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointService pointService;
    String memberNickname1="testMember1";
    String memberNickname2="testMember2";
    @BeforeEach
    void setUp() {
        Member member1 = Member.builder()
                .name("1")
                .nickname(memberNickname1)
                .passWord("ghj")
                .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
                .name("1")
                .nickname(memberNickname2)
                .passWord("ghj")
                .build();
        memberRepository.save(member2);
    }

    @Test
    void chargePoint() {
        Member member = memberRepository.findMemberByNickname(memberNickname1);

        Long amount=50L;
        pointService.chargePoint(member.getNickname(),amount);

        Assertions.assertThat(member.getPoint()).isEqualTo(amount);
    }

    @Test
    void withdrawPoint() {
        Long initAmount = 86L;
        Member member = memberRepository.findMemberByNickname(memberNickname1);
        pointService.chargePoint(member.getNickname(),initAmount);

        Long withdrawAmount=48L;
        pointService.withdrawPoint(member.getNickname(),48L);

        Assertions.assertThat(member.getPoint()).isEqualTo(initAmount-withdrawAmount);
    }

    @Test
    void remittancePoint() {
    }
}