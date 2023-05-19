package com.backend.backend.service;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.post.Post;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.pointDetailRepository.PointDetailRepository;
import com.backend.backend.repository.postRepository.PostRepository;
import com.backend.backend.repository.transactionDetailRepository.TransactionDetailRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TalentTradingServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointService pointService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TalentTradingService talentTradingService;

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;

    @Autowired
    private PointDetailRepository pointDetailRepository;


    String sellerNickname ="testMember1";
    String buyerNickname ="testMember2";



    @BeforeEach
    void setUp() {
        Member member1 = Member.builder()
                .name("1")
                .nickname(sellerNickname)
                .passWord("ghj")
                .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
                .name("1")
                .nickname(buyerNickname)
                .passWord("ghj")
                .build();
        memberRepository.save(member2);
    }

    @Test
    void talentTrading() {
        Long buyerPoint = 200L;
        pointService.chargePoint(buyerNickname,"test",buyerPoint);

        Long price = 110L;
        Post post1 = Post.builder()
                .writer(memberRepository.findMemberByNickname(sellerNickname))
                .postName("가나다 라")
                .content(",")
                .price(price)
                .build();
        Long postId = postRepository.save(post1);


        talentTradingService.talentTrading(postId,buyerNickname);

        Assertions.assertThat(
                transactionDetailRepository.findDetailsByMember(
                        memberRepository.findMemberByNickname(buyerNickname)).size())
                .isEqualTo(1);

        Assertions.assertThat(
                        transactionDetailRepository.findDetailsByMember(
                                memberRepository.findMemberByNickname(sellerNickname)).size())
                .isEqualTo(1);

        Assertions.assertThat(
                        pointDetailRepository.findDetailsByMember(
                                memberRepository.findMemberByNickname(buyerNickname)).size())
                .isEqualTo(2);

        Assertions.assertThat(
                        pointDetailRepository.findDetailsByMember(
                                memberRepository.findMemberByNickname(sellerNickname)).size())
                .isEqualTo(1);

        Assertions.assertThat(memberRepository.findMemberByNickname(buyerNickname).getPoint().getAmount())
                .isEqualTo(buyerPoint-price);

        Assertions.assertThat(memberRepository.findMemberByNickname(sellerNickname).getPoint().getAmount())
                .isEqualTo(price);


    }
}