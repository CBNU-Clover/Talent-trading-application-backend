package com.backend.backend.repository.ratingRepository;

import com.backend.backend.domain.member.Member;
import com.backend.backend.domain.rating.RatingCategory;
import com.backend.backend.domain.rating.Rating;

import java.util.List;

public interface RatingRepository {

    /**
     * 해당 카테고리의 탑 20의 랭킹을 반환해 주는 메소드
     * @param category
     * @return
     */
    List<Rating> getTopRanking(RatingCategory category);

    /**
     * 해당 카테고리에 amount 점수를 추가, 만약 해당카테고리에 대한 점수가 처음 들어가는 것이라면 해당 튜플 생성
     * @param category
     * @param amount 추가될 스코어 점수
     */
    Long addRating(Member member,RatingCategory category, Long amount);

    Rating findRatingById(Long id);
}
