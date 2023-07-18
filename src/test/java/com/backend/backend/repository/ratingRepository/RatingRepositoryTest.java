package com.backend.backend.repository.ratingRepository;

import com.backend.backend.Fixture;
import com.backend.backend.TestSetting;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.post.values.PostCategory;
import com.backend.backend.mvc.domain.rating.Rating;
import com.backend.backend.mvc.repository.memberRepository.MemberRepository;
import com.backend.backend.mvc.repository.ratingRepository.RatingRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;


class RatingRepositoryTest extends TestSetting {

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
        Long id = ratingRepository.addRating(member, PostCategory.CATEGORY1, initScore);

        Assertions.assertThat(ratingRepository.findRatingById(id).getScore()).isEqualTo(initScore);

        Long score = 20L;
        ratingRepository.addRating(member, PostCategory.CATEGORY1,score);

        Assertions.assertThat(ratingRepository.findRatingById(id).getScore()).isEqualTo(initScore+score);
    }

    @Test
    void findRatingByNicknameAndCategory(){
        Long otherScore = 10L;
        ratingRepository.addRating(member, PostCategory.OTHER, otherScore);

        Rating rating = ratingRepository.findRatingByNicknameAndCategory(
                member.getNickname().toString(), PostCategory.OTHER);
        Assertions.assertThat(rating.getScore()).isEqualTo(otherScore);
    }
}