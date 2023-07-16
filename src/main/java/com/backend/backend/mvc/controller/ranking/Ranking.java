package com.backend.backend.mvc.controller.ranking;

import com.backend.backend.mvc.controller.ranking.dto.ResponseRakingList;
import com.backend.backend.mvc.domain.rating.Rating;
import com.backend.backend.mvc.domain.post.values.PostCategory;
import com.backend.backend.mvc.service.ranking.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class Ranking {
    private final RankingService rankingService;

    @GetMapping("/top/{category}")
    public ResponseEntity<ResponseRakingList> getRanking(@PathVariable("category") PostCategory category){

        List<Rating> topRating = rankingService.getTopRating(category);

        return new ResponseEntity<>(new ResponseRakingList(topRating), HttpStatus.OK);
    }
}
