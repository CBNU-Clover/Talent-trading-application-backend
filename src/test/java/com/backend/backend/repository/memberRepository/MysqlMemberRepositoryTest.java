package com.backend.backend.repository.memberRepository;

import com.backend.backend.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MysqlMemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Test
    void save() {
        Member member = Member.builder()
                .name("1")
                .nickName("11")
                .passWord("ghj")
                .build();
        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findMemberById(memberId)).isSameAs(member);

    }

    @Test
    void emailDuplicateCheck() {
        Member member = Member.builder()
                .name("1")
                .nickName("11")
                .passWord("ghj")
                .email("819819519519")
                .build();
        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.emailDuplicateCheck("819819519519")).isTrue();

    }

    @Test
    void nicknameDuplicateCheck() {
        Member member = Member.builder()
                .name("1")
                .nickName("516516516516")
                .passWord("ghj")
                .email("456")
                .build();
        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.nicknameDuplicateCheck("516516516516")).isTrue();

    }


    @Test
    void findMemberById() {
        Member member1 = Member.builder()
                .name("1")
                .nickName("11")
                .passWord("ghj")
                .build();
        Long memberId1 = memberRepository.save(member1);
        Member member2 = Member.builder()
                .name("11")
                .nickName("111")
                .passWord("ghj")
                .build();
        Long memberId2 = memberRepository.save(member2);

        assertThat(memberRepository.findMemberById(memberId2)).isSameAs(member2);
        assertThat(memberRepository.findMemberById(memberId1)).isSameAs(member1);
    }

    @Test
    void deleteMemberById() {
        Member member1 = Member.builder()
                .name("1")
                .nickName("11")
                .passWord("ghj")
                .build();
        Long memberId1 = memberRepository.save(member1);
        Member member2 = Member.builder()
                .name("11")
                .nickName("111")
                .passWord("ghj")
                .build();
        Long memberId2 = memberRepository.save(member2);

        memberRepository.deleteMemberById(memberId1);
        assertThat(memberRepository.findMemberById(memberId1)).isSameAs(null);
        org.junit.jupiter.api.Assertions.assertThrows(InvalidDataAccessApiUsageException.class, ()->{
            memberRepository.deleteMemberById(memberId1);
        });

    }
}