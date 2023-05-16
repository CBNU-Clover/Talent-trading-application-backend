package com.backend.backend.service;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.member.Point;
import com.backend.backend.domain.pointDetail.PointDetail;
import com.backend.backend.domain.pointDetail.PointStatus;
import com.backend.backend.exception.NotExistException;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.pointDetailRepository.PointDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {
    private final MemberRepository memberRepository;
    private final PointDetailRepository pointDetailRepository;

    /**
     * 포인트를 충전하는 메소드
     * @param nickname 충전할 사람
     * @param amount 충전할 포인트 양
     */
    @Transactional
    public void chargePoint(String nickname, String sender, Long amount, String detail){
        Member member=memberRepository.findMemberByNickname(nickname);
        Point memberPoint = member.getPoint();
        PointDetail pointDetail = PointDetail.builder()
                .owner(member)
                .recipient(nickname)
                .sender(sender)
                .status(PointStatus.DEPOSIT)
                .amount(amount)
                .build();
        if(memberPoint==null){
            throw new NotExistException("해당 회원이 존재하지 않습니다");
        }
        memberPoint.addPoint(amount);
        pointDetailRepository.save(pointDetail);
    }

    /**
     * 포인트를 인출하는 메소드
     * @param nickname 인출할 사람
     * @param amount 인출할 포인트양
     */
    @Transactional
    public void withdrawPoint(String nickname, String recipient, Long amount){
        Member member=memberRepository.findMemberByNickname(nickname);
        Point memberPoint = member.getPoint();
        if(memberPoint==null){
            throw new NotExistException("해당 회원이 존재하지 않습니다");
        }
        PointDetail pointDetail = PointDetail.builder()
                .owner(member)
                .recipient(recipient)
                .sender(nickname)
                .status(PointStatus.DEPOSIT)
                .amount(amount)
                .build();
        memberPoint.subPoint(amount);
        pointDetailRepository.save(pointDetail);
    }

    /**
     * sender가 receiver에게 포인트를 송금
     * @param senderNickname 포인트를 보내는 사람
     * @param receiverNickname 포인트를 받는 사람
     * @param amount 포인트 양
     */
    @Transactional
    public void remittancePoint(String senderNickname,String receiverNickname, Long amount){
        Point senderPoint = memberRepository.findMemberByNickname(senderNickname).getPoint();
        Point receiverPoint = memberRepository.findMemberByNickname(receiverNickname).getPoint();
        if(senderPoint==null||receiverPoint==null){
            throw new NotExistException("해당 회원이 존재하지 않습니다");
        }
        senderPoint.subPoint(amount);
        receiverPoint.addPoint(amount);
    }

}
