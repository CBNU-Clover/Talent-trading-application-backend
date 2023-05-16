package com.backend.backend.service;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.pointDetail.PointDetail;
import com.backend.backend.exception.pointException.PointAmountError;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.pointDetailRepository.PointDetailRepository;
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

    @Autowired
    private PointDetailRepository pointDetailRepository;
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
        pointService.chargePoint(member.getNickname(),"test",amount,"");

        Assertions.assertThat(member.getPoint().getAmount()).isEqualTo(amount);
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(member).size()).isEqualTo(1);
    }

    @Test
    void chargePointError() {
        Member member = memberRepository.findMemberByNickname(memberNickname1);
        Long amount=-50L;

        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            pointService.chargePoint(member.getNickname(),"test",amount,"");
        });
    }

    @Test
    void withdrawPoint() {
        Long initAmount = 86L;
        Member member = memberRepository.findMemberByNickname(memberNickname1);
        pointService.chargePoint(member.getNickname(),"test",initAmount,"");

        Long withdrawAmount=48L;
        pointService.withdrawPoint(member.getNickname(),withdrawAmount);

        Assertions.assertThat(member.getPoint().getAmount()).isEqualTo(initAmount-withdrawAmount);
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(member).size()).isEqualTo(1);
    }

    @Test
    void withdrawPointError() {
        Long initAmount = 86L;
        Member member = memberRepository.findMemberByNickname(memberNickname1);
        pointService.chargePoint(member.getNickname(),"test",initAmount,"");


        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            Long withdrawAmount=-48L;
            pointService.withdrawPoint(member.getNickname(),withdrawAmount);
        });

        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            Long withdrawAmount=90L;
            pointService.withdrawPoint(member.getNickname(),withdrawAmount);
        });
    }

    @Test
    void remittancePoint() {
        Long initAmount = 86L;
        Member sender = memberRepository.findMemberByNickname(memberNickname1);
        Member receiver = memberRepository.findMemberByNickname(memberNickname2);
        pointService.chargePoint(sender.getNickname(),"test",initAmount,"");

        Long remittanceAmount=48L;
        pointService.remittancePoint(sender.getNickname(),receiver.getNickname(),remittanceAmount);

        Assertions.assertThat(sender.getPoint().getAmount()).isEqualTo(initAmount-remittanceAmount);
        Assertions.assertThat(receiver.getPoint().getAmount()).isEqualTo(remittanceAmount);
    }

    @Test
    void remittancePointError() {
        Long initAmount = 86L;
        Member sender = memberRepository.findMemberByNickname(memberNickname1);
        Member receiver = memberRepository.findMemberByNickname(memberNickname2);
        pointService.chargePoint(sender.getNickname(),"test",initAmount,"");


        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            Long remittanceAmount=-48L;
            pointService.remittancePoint(sender.getNickname(),receiver.getNickname(),remittanceAmount);
        });

        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            Long remittanceAmount=initAmount+20L;
            pointService.remittancePoint(sender.getNickname(),receiver.getNickname(),remittanceAmount);
        });
    }
}