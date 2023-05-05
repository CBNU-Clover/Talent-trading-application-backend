package com.backend.backend.service;

import com.backend.backend.domain.member.Member;
import com.backend.backend.exception.NotExistException;
import com.backend.backend.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {
    private final MemberRepository memberRepository;

    /**
     * 포인트를 충전하는 메소드
     * @param nickname 충전할 사람
     * @param amount 충전할 포인트 양
     */
    @Transactional
    public void chargePoint(String nickname, Long amount){
        Member member = memberRepository.findMemberByNickname(nickname);
        if(member==null){
            throw new NotExistException("해당 회원이 존재하지 않습니다");
        }
        member.addPoint(amount);
    }

    /**
     * 포인트를 인출하는 메소드
     * @param nickname 인출할 사람
     * @param amount 인출할 포인트양
     */
    @Transactional
    public void withdrawPoint(String nickname, Long amount){

    }

    /**
     * sender가 receiver에게 포인트를 송금
     * @param sender 포인트를 보내는 사람
     * @param receiver 포인트를 받는 사람
     * @param amount 포인트 양
     */
    @Transactional
    public void remittancePoint(String sender,String receiver, Long amount){

    }

}
