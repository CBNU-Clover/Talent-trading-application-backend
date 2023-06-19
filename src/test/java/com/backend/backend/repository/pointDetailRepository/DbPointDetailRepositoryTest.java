package com.backend.backend.repository.pointDetailRepository;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class DbPointDetailRepositoryTest extends TestSetting {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointDetailRepository pointDetailRepository;

    Member member;

    @BeforeEach
    void setup(){
        member = Fixture.createMember("1");
        memberRepository.save(member);

    }

    @Test
    void save() {
        PointDetail pointDetail = PointDetail.builder()
                .owner(member)
                .recipient("a")
                .sender("b")
                .status(PointStatus.DEPOSIT)
                .amount(10L)
                .build();
        Long id = pointDetailRepository.save(pointDetail);
        Assertions.assertThat(pointDetailRepository.findDetailById(id)).isSameAs(pointDetail);
    }

    @Test
    void findDetailsByMember() {
        PointDetail pointDetail1 = PointDetail.builder()
                .owner(member)
                .recipient("a")
                .sender("b")
                .status(PointStatus.DEPOSIT)
                .amount(10L)
                .build();
        pointDetailRepository.save(pointDetail1);
        PointDetail pointDetail2 = PointDetail.builder()
                .owner(member)
                .recipient("a")
                .sender("b")
                .status(PointStatus.DEPOSIT)
                .amount(10L)
                .build();
        pointDetailRepository.save(pointDetail2);

        List<PointDetail> pointDetails = pointDetailRepository.findDetailsByMember(member);
        Assertions.assertThat(pointDetails.size()).isEqualTo(2);
        Assertions.assertThat(pointDetails).contains(pointDetail1, pointDetail2);

    }
}