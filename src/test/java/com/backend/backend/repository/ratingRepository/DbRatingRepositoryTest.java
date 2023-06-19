package com.backend.backend.repository.ratingRepository;

import com.backend.backend.Fixture;
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
        member = Fixture.createMember("1");
        memberRepository.save(member);
    }

    @Test
    void addRating() {
        Long initScore = 10L;
        Long id = ratingRepository.addRating(member, RatingCategory.CATEGORY1, initScore);

        Assertions.assertThat(ratingRepository.findRatingById(id).getScore()).isEqualTo(initScore);

        Long score = 20L;
        ratingRepository.addRating(member, RatingCategory.CATEGORY1,score);

        Assertions.assertThat(ratingRepository.findRatingById(id).getScore()).isEqualTo(initScore+score);
    }
}