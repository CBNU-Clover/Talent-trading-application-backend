package com.backend.backend.repository.pointDetailRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.pointDetail.PointDetail;
import com.backend.backend.domain.post.Post;

import java.util.List;

public interface PointDetailRepository {


    /**
     * point거래 기록 저장
     * @param pointDetail 포인트 거래 기록
     * @return
     */
    Long save(PointDetail pointDetail);

    /**
     * 해당 회원의 포인트 거래 기록 반환
     * @param member 회원 객체
     * @return
     */
    List<PointDetail> findPointDetailsByMember(Member member);


}
