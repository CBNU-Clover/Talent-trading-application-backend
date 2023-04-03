package com.backend.backend.repository.memberRepository;

import com.backend.backend.domain.Member;
import com.backend.backend.domain.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.backend.backend.domain.QMember.member;
import static com.backend.backend.domain.QPost.post;

@Repository
public class MysqlMemberRepository implements MemberRepository{
    
    private final EntityManager em;
    private final JPAQueryFactory query;

    public MysqlMemberRepository(EntityManager em) {
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
    public void deleteMemberById(Long id) {
        Member member = findMemberById(id);
        em.remove(member);
    }

    @Override
    public List<Member> findAll(MemberSearch memberSearch) {
        return query
                .select(member)
                .from(member)
                .where(nickNameLike(memberSearch.getNickName()))
                .limit(100)
                .fetch();
    }

    @Override
    public Boolean nicknameDuplicateCheck(String nickName) {
        Integer fetchOne = query
                .selectOne()
                .from(member)
                .where(member.nickName.eq(nickName))
                .fetchFirst();
        return fetchOne!=null;
    }

    private BooleanExpression nickNameLike(String name){
        if(!StringUtils.hasText(name)){
            return null;
        }
        return post.postName.like(name);
    }
}
