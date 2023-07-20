package com.backend.backend.mvc.service;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.Post;
import com.backend.backend.mvc.domain.post.values.PostCategory;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.pointDetailRepository.PointDetailRepository;
import com.backend.backend.mvc.repository.postRepository.PostRepository;
import com.backend.backend.mvc.repository.ratingRepository.RatingRepository;
import com.backend.backend.mvc.repository.transactionDetailRepository.TransactionDetailRepository;
import com.backend.backend.mvc.service.PointService;
import com.backend.backend.mvc.service.TalentTradingService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class TalentTradingServiceTest extends TestSetting {

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

    @Autowired
    private RatingRepository ratingRepository;


    String sellerNickname;
    String buyerNickname;



    @BeforeEach
    void setUp() {
        Member member1 = Fixture.createMember("1");
        sellerNickname = member1.getNickname().toString();
        memberRepository.save(member1);

        Member member2 = Fixture.createMember("2");
        buyerNickname = member2.getNickname().toString();
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
                .category(PostCategory.OTHER)
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

        Assertions.assertThat(ratingRepository.findRatingByNicknameAndCategory(
                        sellerNickname, PostCategory.OTHER).getScore())
                .isEqualTo(10L);

        Assertions.assertThat(ratingRepository.findRatingByNicknameAndCategory(
                        buyerNickname, PostCategory.OTHER).getScore())
                .isEqualTo(10L);

    }
}