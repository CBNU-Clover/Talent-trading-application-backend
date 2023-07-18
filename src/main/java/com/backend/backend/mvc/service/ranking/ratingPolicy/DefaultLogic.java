package com.backend.backend.mvc.service.ranking.ratingPolicy;

import org.springframework.stereotype.Component;

@Component
public class DefaultLogic implements RatingPolicy{
    @Override
    public Long calculate() {
        return 10L;
    }
}
