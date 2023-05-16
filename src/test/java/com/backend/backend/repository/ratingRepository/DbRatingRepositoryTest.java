package com.backend.backend.repository.ratingRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.member.RatingCategory;
import com.backend.backend.repository.memberRepository.MemberRepository;
import com.backend.backend.repository.pointDetailRepository.PointDetailRepository;
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
class DbRatingRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RatingRepository ratingRepository;

    Member member;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .name("415646556456")
                .nickname("huysdagsibib")
                .passWord("5456")
                .email("566511561sd1")
                .build();
        memberRepository.save(member);
    }

    @Test
    void addRating() {
        ratingRepository.addRating(member, RatingCategory.CATEGORY1,10L);

        ratingRepository.addRating(member, RatingCategory.CATEGORY1,30L);
    }
}