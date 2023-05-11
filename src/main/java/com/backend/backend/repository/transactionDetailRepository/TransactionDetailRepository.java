package com.backend.backend.repository.transactionDetailRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.pointDetail.PointDetail;
import com.backend.backend.domain.transactionDetail.TransactionDetail;

import java.util.List;

public interface TransactionDetailRepository {

    /**
     * 재능거래 기록 저장
     * @param transactionDetail 재능거래 기록
     * @return
     */
    Long save(TransactionDetail transactionDetail);

    /**
     * 해당회원의 재능거래 기록을 반환
     * @param member 회원 객체
     * @return
     */
    List<TransactionDetail> findDetailsByMember(Member member);
}
