package com.backend.backend.service;

import com.backend.backend.domain.member.Rating;
import com.backend.backend.domain.member.RatingCategory;
import com.backend.backend.repository.ratingRepository.RatingRepository;
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
