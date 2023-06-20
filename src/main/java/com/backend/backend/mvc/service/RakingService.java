package com.backend.backend.mvc.service;

import com.backend.backend.mvc.domain.rating.Rating;
import com.backend.backend.mvc.domain.rating.values.RatingCategory;
import com.backend.backend.mvc.repository.ratingRepository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RakingService {
    private final RatingRepository ratingRepository;

    public List<Rating> getTopRating(RatingCategory category){
        return ratingRepository.getTopRanking(category);
    }
}
