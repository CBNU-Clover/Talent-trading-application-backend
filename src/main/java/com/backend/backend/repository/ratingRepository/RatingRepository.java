package com.backend.backend.repository.ratingRepository;

import com.backend.backend.domain.member.RatingCategory;
import com.backend.backend.domain.member.Rating;

import java.util.List;

public interface RatingRepository {

    /**
     * 헤당 카테고리의 탑 20의 랭킹을 반환해 주는 메소드
     * @return
     */
    List<Rating> getTopRanking(RatingCategory category);
}
