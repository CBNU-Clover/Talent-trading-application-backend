package com.backend.backend.repository.pointDetailRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.pointDetail.PointDetail;
import com.backend.backend.domain.pointDetail.PointStatus;
import com.backend.backend.repository.memberRepository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    //@Rollback(value = false)
    void save() {
        PointDetail pointDetail = PointDetail.builder()
                .owner(member)
                .recipient("a")
                .sender("b")
                .status(PointStatus.CREDIT)
                .build();
        Long id = pointDetailRepository.save(pointDetail);
        System.out.println("pointDetail = " + pointDetail.getDate());
        Assertions.assertThat(pointDetailRepository.findDetailById(id)).isSameAs(pointDetail);
    }

    @Test
    void findDetailsByMember() {
        PointDetail pointDetail1 = PointDetail.builder()
                .owner(member)
                .recipient("a")
                .sender("b")
                .status(PointStatus.CREDIT)
                .build();
        pointDetailRepository.save(pointDetail1);
        PointDetail pointDetail2 = PointDetail.builder()
                .owner(member)
                .recipient("a")
                .sender("b")
                .status(PointStatus.CREDIT)
                .build();
        pointDetailRepository.save(pointDetail2);

        List<PointDetail> pointDetails = pointDetailRepository.findDetailsByMember(member);
        Assertions.assertThat(pointDetails.size()).isEqualTo(2);
        Assertions.assertThat(pointDetails).contains(pointDetail1, pointDetail2);

    }
}