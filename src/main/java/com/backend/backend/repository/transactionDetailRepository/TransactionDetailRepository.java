package com.backend.backend.repository.transactionDetailRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.pointDetail.PointDetail;
import com.backend.backend.domain.transactionDetail.TransactionDetail;

import java.util.List;

public interface TransactionDetailRepository {

    Long save(TransactionDetail transactionDetail);

    List<TransactionDetail> findDetailsByMember(Member member);
}
