package com.backend.backend.repository.rankingRepository;

import com.backend.backend.domain.member.Rating;

import java.util.List;

public interface RankingRepository {

    /**
     * 헤당 카테고리의 탑 20의 랭킹을 반환해 주는 메소드
     * @return
     */
    List<Rating> getTopRanking(RankingCategory category);
}
