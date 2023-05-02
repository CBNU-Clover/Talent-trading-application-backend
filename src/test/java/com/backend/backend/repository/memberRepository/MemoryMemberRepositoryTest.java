package com.backend.backend.repository.memberRepository;

import com.backend.backend.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MemoryMemberRepositoryTest {

    MemberRepository memberRepository;

    @BeforeEach
    void init(){
        memberRepository=new MemoryMemberRepository();
    }

    @Test
    void save() {
        Member member = Member.builder()
                .name("1")
                .nickname("11")
                .email(".")
                .phoneNumber("111")
                .passWord("ghj")
                .point(0L)
                .build();
        Long id = memberRepository.save(member);
        Member findMember = memberRepository.findMemberById(id);
        Assertions.assertThat(findMember).isSameAs(member);
    }

    @Test
    void findMemberById() {
        Map<Long, Member> memberMap = new HashMap<>();
        for(int i=0;i<10;i++) {
            Member member = Member.builder()
                    .name("1")
                    .nickname("11")
                    .passWord("ghj")
                    .build();
            Long id = memberRepository.save(member);
            memberMap.put(id, member);
        }
        for(Long id: memberMap.keySet()){
            Member member = memberRepository.findMemberById(id);
            Assertions.assertThat(member).isSameAs(memberMap.get(id));
        }
    }
}