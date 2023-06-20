package com.backend.backend.mvc.repository.memberRepository;

import com.backend.backend.mvc.domain.member.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.mvc.domain.member.QMember.member;
import static com.backend.backend.mvc.domain.post.QPost.post;


@Repository
public class DbMemberRepository implements MemberRepository{
    
    private final EntityManager em;
    private final JPAQueryFactory query;

    public DbMemberRepository(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

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
    public Member findMemberByNickname(String nickname) {
        return query
                .select(member)
                .from(member)
                .where(member.nickname.nickname.eq(nickname))
                .fetchOne();
    }

    @Override
    public void deleteMemberById(Long id) {
        Member member = findMemberById(id);
        em.remove(member);
    }

    @Override
    public List<Member> findAll(MemberSearch memberSearch) {
        return query
                .select(member)
                .from(member)
                .where(nicknameLike(memberSearch.getNickname()))
                .limit(100)
                .fetch();
    }

    @Override
    public Boolean nicknameDuplicateCheck(String nickname) {
        Integer fetchOne = query
                .selectOne()
                .from(member)
                .where(member.nickname.nickname.eq(nickname))
                .fetchFirst();
        return fetchOne!=null;
    }

    @Override
    public Boolean emailDuplicateCheck(String email) {
        Integer fetchOne = query
                .selectOne()
                .from(member)
                .where(member.email.email.eq(email))
                .fetchFirst();
        return fetchOne!=null;
    }


    private BooleanExpression nicknameLike(String name){
        if(!StringUtils.hasText(name)){
            return null;
        }
        return post.postName.postName.like(name);
    }
}
