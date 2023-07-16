package com.backend.backend.mvc.service.ranking.ratingPolicy;

public interface RatingPolicy {
    /**
     * 추가될 레이팅을 계산해주는 기능
     * @return 계산된 값을 반환
     */
    Long calculate();
}
