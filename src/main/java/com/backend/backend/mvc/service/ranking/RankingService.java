package com.backend.backend.mvc.service.ranking;

import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.rating.Rating;
import com.backend.backend.mvc.domain.rating.values.RatingCategory;
import com.backend.backend.mvc.repository.ratingRepository.RatingRepository;
import com.backend.backend.mvc.service.ranking.ratingPolicy.RatingPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {
    private final RatingRepository ratingRepository;
    private final RatingPolicy ratingPolicy;

    public List<Rating> getTopRating(RatingCategory category){
        return ratingRepository.getTopRanking(category);
    }

    public void addRating(Member member,RatingCategory category){
        Long amount = ratingPolicy.calculate();
        ratingRepository.addRating(member, category, amount);
    }
}
