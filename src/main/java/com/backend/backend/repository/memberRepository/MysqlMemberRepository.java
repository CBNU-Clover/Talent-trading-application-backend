package com.backend.backend.repository.memberRepository;

import com.backend.backend.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository
@RequiredArgsConstructor
public class MysqlMemberRepository implements MemberRepository{
    
    private final EntityManager em;

    @Override
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    @Override
    public Member findMemberById(Long id) {
        return em.find(Member.class,id);
    }

    @Override
    public void deleteMemberById(Long id) {
        Member member = findMemberById(id);
        em.remove(member);
    }
}
