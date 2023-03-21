package com.backend.backend.repository.memberRepository;

import com.backend.backend.domain.Member;

public interface MemberRepository {
    /**
     * Member객체 전달시 레파지토리에 저장됨
     * @param member
     * @return
     */
    Long save(Member member);

    /**
     * id를 이용해서 Member객체 검색
     * @param id
     * @return
     */
    Member findMemberById(Long id);

    /**
     * id를 이용해서 Member 객체 삭제
     * @param id
     */
    void deleteMemberById(Long id);

}
