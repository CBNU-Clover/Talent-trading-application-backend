package com.backend.backend.service;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.post.Post;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.postRepository.PostRepository;
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

    String sellerNickname ="testMember1";
    String buyerNickname ="testMember2";

    Long postId;


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
        pointService.chargePoint(buyerNickname,"test",200L);


        Post post1 = Post.builder()
                .writer(memberRepository.findMemberByNickname(sellerNickname))
                .postName("가나다 라")
                .content(",")
                .price(100L)
                .build();
        postId = postRepository.save(post1);
    }

    @Test
    void talentTrading() {
    }
}