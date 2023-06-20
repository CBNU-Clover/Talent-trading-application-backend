package com.backend.backend.repository.memberRepository;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class MemberRepositoryTest extends TestSetting {

    @Autowired
    private MemberRepository memberRepository;
    @Test
    void save() {
        Member member = Fixture.createMember("1");
        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findMemberById(memberId)).isSameAs(member);

    }

    @Test
    void emailDuplicateCheck() {
        Member member = Member.builder()
                .name("가나다")
                .nickname("a123")
                .password("ghj")
                .email("test@test.com")
                .phoneNumber("01012345678")
                .build();
        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.emailDuplicateCheck("test@test.com")).isTrue();

    }

    @Test
    void nicknameDuplicateCheck() {
        Member member = Member.builder()
                .name("가나다")
                .nickname("516516516516a")
                .password("ghj")
                .email("test@test.com")
                .phoneNumber("01012345678")
                .build();
        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.nicknameDuplicateCheck("516516516516a")).isTrue();

    }


    @Test
    void findMemberById() {
        Member member1 = Fixture.createMember("1");
        Long memberId1 = memberRepository.save(member1);
        Member member2 = Fixture.createMember("2");
        Long memberId2 = memberRepository.save(member2);

        assertThat(memberRepository.findMemberById(memberId2)).isSameAs(member2);
        assertThat(memberRepository.findMemberById(memberId1)).isSameAs(member1);
    }

    @Test
    void deleteMemberById() {
        Member member1 = Fixture.createMember("1");
        Long memberId1 = memberRepository.save(member1);
        Member member2 = Fixture.createMember("2");
        Long memberId2 = memberRepository.save(member2);

        memberRepository.deleteMemberById(memberId1);
        assertThat(memberRepository.findMemberById(memberId1)).isSameAs(null);
        org.junit.jupiter.api.Assertions.assertThrows(InvalidDataAccessApiUsageException.class, ()->{
            memberRepository.deleteMemberById(memberId1);
        });

    }
}