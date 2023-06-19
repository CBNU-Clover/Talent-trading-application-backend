package com.backend.backend.repository.transactionDetailRepository;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.transactionDetail.TransactionDetail;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.transactionDetailRepository.TransactionDetailRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


class TransactionDetailRepositoryTest extends TestSetting {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    Member member1;
    Member member2;

    @BeforeEach
    void setup(){
        member1 = Fixture.createMember("1");
        memberRepository.save(member1);
        member2 = Fixture.createMember("2");
        memberRepository.save(member2);
    }

    @Test
    void save() {
        TransactionDetail detail1 = TransactionDetail.builder()
                .buyer(member1)
                .seller(member2)
                .build();
        Long id1 = transactionDetailRepository.save(detail1);
        TransactionDetail detail2 = TransactionDetail.builder()
                .buyer(member2)
                .seller(member1)
                .build();
        Long id2 = transactionDetailRepository.save(detail2);

        Assertions.assertThat(transactionDetailRepository.findDetailById(id1))
                .isSameAs(detail1);

        Assertions.assertThat(transactionDetailRepository.findDetailById(id2))
                .isSameAs(detail2);
    }

    @Test
    void findDetailsByMember() {
        TransactionDetail detail1 = TransactionDetail.builder()
                .buyer(member1)
                .seller(member2)
                .build();
        transactionDetailRepository.save(detail1);
        TransactionDetail detail2 = TransactionDetail.builder()
                .buyer(member1)
                .seller(member2)
                .build();
        transactionDetailRepository.save(detail2);

        List<TransactionDetail> details =
                transactionDetailRepository.findDetailsByMember(member1);

        Assertions.assertThat(details.size()).isEqualTo(2);
        Assertions.assertThat(details.get(0)).isSameAs(detail1);
        Assertions.assertThat(details.get(1)).isSameAs(detail2);
    }
}