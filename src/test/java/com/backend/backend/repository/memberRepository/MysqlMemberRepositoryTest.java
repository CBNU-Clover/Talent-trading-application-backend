package com.backend.backend.repository.memberRepository;

import com.backend.backend.config.JasyptConfig;
import com.backend.backend.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Import(JasyptConfig.class)
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
                .email(".")
                .phoneNumber("111")
                .passWord("ghj")
                .build();
        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findMemberById(memberId)).isSameAs(member);

    }

    @Test
    void findMemberById() {

    }

    @Test
    void deleteMemberById() {
    }
}