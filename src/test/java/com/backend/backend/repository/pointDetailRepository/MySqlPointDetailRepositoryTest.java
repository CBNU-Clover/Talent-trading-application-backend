package com.backend.backend.repository.pointDetailRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.pointDetail.PointDetail;
import com.backend.backend.domain.pointDetail.PointStatus;
import com.backend.backend.repository.memberRepository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MySqlPointDetailRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointDetailRepository pointDetailRepository;

    Member member;

    @BeforeEach
    void setup(){
        member = Member.builder()
                .name("415646556456")
                .nickname("huysdagsibib")
                .passWord("5456")
                .email("566511561sd1")
                .build();
        memberRepository.save(member);

    }

    @Test
    void save() {
        PointDetail pointDetail = PointDetail.builder()
                .owner(member)
                .recipient("a")
                .sender("b")
                .status(PointStatus.CREDIT)
                .build();
        pointDetailRepository.save(pointDetail);

    }

    @Test
    void findDetailsByMember() {
    }
}