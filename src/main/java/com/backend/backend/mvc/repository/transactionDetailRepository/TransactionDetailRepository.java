package com.backend.backend.mvc.repository.transactionDetailRepository;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.transactionDetail.TransactionDetail;

import java.util.List;

public interface TransactionDetailRepository {

    /**
     * 재능거래 기록 저장
     * @param transactionDetail 재능거래 기록
     * @return
     */
    Long save(TransactionDetail transactionDetail);

    /**
     * 아이디로 재능거래 한 건을 찾음
     * @param id 재능거래기록의 아이디
     * @return
     */
    TransactionDetail findDetailById(Long id);

    /**
     * 해당회원의 재능거래 기록을 반환
     * @param member 회원 객체
     * @return
     */
    List<TransactionDetail> findDetailsByMember(Member member);
}
