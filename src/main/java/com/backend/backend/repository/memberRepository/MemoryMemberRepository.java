package com.backend.backend.repository.memberRepository;

import com.backend.backend.domain.member.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store =new HashMap<>();

    @Override
    public Long save(Member member) {

        store.put(member.getId(), member);
        return member.getId();
    }

    @Override
    public Member findMemberById(Long id) {
        return store.get(id);
    }

    @Override
    public Member findMemberByNickname(String nickname) {
        return null;
    }

    @Override
    public void deleteMemberById(Long id) {

    }

    @Override
    public List<Member> findAll(MemberSearch memberSearch) {
        return null;
    }

    @Override
    public Boolean nicknameDuplicateCheck(String nickname) {
        return null;
    }

    @Override
    public Boolean emailDuplicateCheck(String email) {
        return null;
    }
}
