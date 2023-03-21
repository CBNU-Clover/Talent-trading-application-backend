package com.backend.backend.repository.memberRepository;

import com.backend.backend.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
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
    public void deleteMemberById(Long id) {

    }
}
