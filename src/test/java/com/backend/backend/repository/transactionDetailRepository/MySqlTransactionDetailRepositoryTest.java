package com.backend.backend.repository.transactionDetailRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.transactionDetail.TransactionDetail;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MySqlTransactionDetailRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    Member member1;
    Member member2;

    @BeforeEach
    void setup(){
        member1 = Member.builder()
                .name("415646556456")
                .nickname("huysdagsibib")
                .passWord("5456")
                .email("566511561sd1")
                .build();
        memberRepository.save(member1);
        member2 = Member.builder()
                .name("415646556456")
                .nickname("huysdagsibib")
                .passWord("5456")
                .email("566511561sd1")
                .build();
        memberRepository.save(member2);
    }

    @Test
    void save() {
        TransactionDetail detail = TransactionDetail.builder()
                .buyer(member1)
                .seller(member2)
                .build();
        Long id = transactionDetailRepository.save(detail);

        Assertions.assertThat(transactionDetailRepository.findDetailById(id))
                .isSameAs(detail);
    }

    @Test
    void findDetailsByMember() {
        TransactionDetail detail1 = TransactionDetail.builder()
                .buyer(member1)
                .seller(member2)
                .build();
        transactionDetailRepository.save(detail1);
        TransactionDetail detail2 = TransactionDetail.builder()
                .buyer(member2)
                .seller(member1)
                .build();
        transactionDetailRepository.save(detail2);

        Assertions.assertThat(
                        transactionDetailRepository.findDetailsByMember(member1).get(0))
                .isSameAs(detail1);
        Assertions.assertThat(
                        transactionDetailRepository.findDetailsByMember(member1).get(1))
                .isSameAs(detail2);

    }
}