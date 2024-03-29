package com.backend.backend.mvc.repository.memberRepository;

import com.backend.backend.mvc.domain.member.Member;

import java.util.List;

public interface MemberRepository{
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
     * 닉네임으로 검색
     * @param nickname
     * @return
     */
    Member findMemberByNickname(String nickname);

    /**
     * id를 이용해서 Member 객체 삭제
     * @param id
     */
    void deleteMemberById(Long id);

    /**
     * MemberSearch의 조건에 맞는 Member 반환
     * member 검색시에 사용
     * @param memberSearch
     * @return
     */
    List<Member> findAll(MemberSearch memberSearch);

    /**
     * 닉네임 중복확인
     * @param nickname
     * @return
     */
    Boolean nicknameDuplicateCheck(String nickname);

    /**
     * 이메일 중복확인
     * @param email
     * @return
     */
    Boolean emailDuplicateCheck(String email);
}
