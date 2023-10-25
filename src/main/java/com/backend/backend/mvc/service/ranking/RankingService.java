package com.backend.backend.mvc.service.ranking;

import com.backend.backend.mvc.controller.ranking.CalculateRanking;
import com.backend.backend.mvc.controller.ranking.dto.MyRanking;
import com.backend.backend.mvc.controller.ranking.dto.ResponseRankingList;
import com.backend.backend.mvc.domain.member.Member;
import com.backend.backend.mvc.domain.rating.Rating;
import com.backend.backend.mvc.domain.post.values.PostCategory;
import com.backend.backend.mvc.repository.ratingRepository.RatingRepository;
import com.backend.backend.mvc.service.ranking.ratingPolicy.RatingPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RankingService {
    private final RatingRepository ratingRepository;
    private final RatingPolicy ratingPolicy;

    //랭킹리스트 반환
    @Transactional
    public List<ResponseRankingList> getTopRating(PostCategory category) {

        List<ResponseRankingList> responseRankingLists = new ArrayList<>();
        List<Rating> ratings = ratingRepository.getTopRanking(PostCategory.OTHER);
        CalculateRanking calculateRanking=new CalculateRanking();
        for (int num = 0; num < ratings.size(); num++) {

            responseRankingLists.add(num, new ResponseRankingList(ratings.get(num).getMember().getNickname().toString(),
                    ratings.get(num).getScore(),
                    calculateRanking.calculateRanking(ratings.get(num).getScore())));
        }
        return responseRankingLists;
    }

    //내 랭킹 반환
    @Transactional
    public MyRanking myRanking(String nickname)
    {
        Rating rating=ratingRepository.findRatingByNicknameAndCategory(nickname,PostCategory.OTHER);
        CalculateRanking calculateRanking=new CalculateRanking();
        String grade=calculateRanking.calculateRanking(rating.getScore());
        MyRanking myRanking=new MyRanking(rating,grade);
        return myRanking;
    }
    @Transactional
    public void addRating(Member member, PostCategory category) {
        Long amount = ratingPolicy.calculate();
        ratingRepository.addRating(member, category, amount);
    }
}

