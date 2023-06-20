package com.backend.backend.service;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.pointDetail.values.PointStatus;
import com.backend.backend.common.exception.pointException.PointAmountError;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.pointDetailRepository.PointDetailRepository;
import com.backend.backend.mvc.service.PointService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class PointServiceTest extends TestSetting {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointService pointService;

    @Autowired
    private PointDetailRepository pointDetailRepository;
    String memberNickname1;
    String memberNickname2;
    @BeforeEach
    void setUp() {
        Member member1 = Fixture.createMember("1");
        memberNickname1 = member1.getNickname().toString();
        memberRepository.save(member1);

        Member member2 = Fixture.createMember("2");
        memberNickname2 = member2.getNickname().toString();
        memberRepository.save(member2);
    }

    @Test
    void chargePoint() {
        Member member = memberRepository.findMemberByNickname(memberNickname1);

        Long amount=50L;
        pointService.chargePoint(member.getNickname().toString(),"test",amount);

        Assertions.assertThat(member.getPoint().getAmount()).isEqualTo(amount);
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(member).size()).isEqualTo(1);
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(member).get(0).getBalance().getBalance())
                .isEqualTo(amount);
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(member).get(0).getStatus())
                .isEqualTo(PointStatus.DEPOSIT);
    }

    @Test
    void chargePointError() {
        Member member = memberRepository.findMemberByNickname(memberNickname1);
        Long amount=-50L;

        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            pointService.chargePoint(member.getNickname().toString(),"test",amount);
        });
    }

    @Test
    void withdrawPoint() {
        Long initAmount = 86L;
        Member member = memberRepository.findMemberByNickname(memberNickname1);
        pointService.chargePoint(member.getNickname().toString(),"test",initAmount);

        Long withdrawAmount=48L;
        pointService.withdrawPoint(member.getNickname().toString(),"test",withdrawAmount);

        Assertions.assertThat(member.getPoint().getAmount()).isEqualTo(initAmount-withdrawAmount);
        //입금, 출금 하나씩 기록됨
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(member).size()).isEqualTo(2);
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(member).get(1).getBalance().getBalance())
                .isEqualTo(initAmount-withdrawAmount);
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(member).get(1).getStatus())
                .isEqualTo(PointStatus.TRANSFER);
    }

    @Test
    void withdrawPointError() {
        Long initAmount = 86L;
        Member member = memberRepository.findMemberByNickname(memberNickname1);
        pointService.chargePoint(member.getNickname().toString(),"test",initAmount);


        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            Long withdrawAmount=-48L;
            pointService.withdrawPoint(member.getNickname().toString(),"test",withdrawAmount);
        });

        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            Long withdrawAmount=90L;
            pointService.withdrawPoint(member.getNickname().toString(),"test",withdrawAmount);
        });
    }

    @Test
    void remittancePoint() {
        Long initAmount = 86L;
        Member sender = memberRepository.findMemberByNickname(memberNickname1);
        Member receiver = memberRepository.findMemberByNickname(memberNickname2);
        pointService.chargePoint(sender.getNickname().toString(),"test",initAmount);

        Long remittanceAmount=48L;
        pointService.remittancePoint(sender.getNickname().toString(),receiver.getNickname().toString(),remittanceAmount);

        Assertions.assertThat(sender.getPoint().getAmount()).isEqualTo(initAmount-remittanceAmount);
        Assertions.assertThat(receiver.getPoint().getAmount()).isEqualTo(remittanceAmount);
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(receiver).size()).isEqualTo(1);
        //입금, 출금 하나씩 기록됨
        Assertions.assertThat(pointDetailRepository.findDetailsByMember(sender).size()).isEqualTo(2);
    }

    @Test
    void remittancePointError() {
        Long initAmount = 86L;
        Member sender = memberRepository.findMemberByNickname(memberNickname1);
        Member receiver = memberRepository.findMemberByNickname(memberNickname2);
        pointService.chargePoint(sender.getNickname().toString(),"test",initAmount);


        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            Long remittanceAmount=-48L;
            pointService.remittancePoint(sender.getNickname().toString(),receiver.getNickname().toString(),remittanceAmount);
        });

        org.junit.jupiter.api.Assertions.assertThrows(PointAmountError.class,()->{
            Long remittanceAmount=initAmount+20L;
            pointService.remittancePoint(sender.getNickname().toString(),receiver.getNickname().toString(),remittanceAmount);
        });
    }
}