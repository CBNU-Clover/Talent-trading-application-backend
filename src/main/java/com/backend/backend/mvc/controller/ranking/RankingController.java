package com.backend.backend.mvc.controller.ranking;


import com.backend.backend.common.DataProcessing.TokenParsing;
import com.backend.backend.mvc.controller.ranking.dto.MyRanking;
import com.backend.backend.mvc.controller.ranking.dto.ResponseRankingList;

import com.backend.backend.mvc.domain.post.values.PostCategory;
import com.backend.backend.mvc.service.ranking.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/vi/ranking")
@RequiredArgsConstructor
public class RankingController {
    private final RankingService rankingService;

    @GetMapping("/ranking")
    public List<ResponseRankingList> Ranking()
    {

        return rankingService.getTopRating(PostCategory.OTHER);
    }

    @GetMapping("/myranking")
    public MyRanking myRanking(HttpServletRequest request)
    {
        TokenParsing tokenParsing=new TokenParsing();
        String result= tokenParsing.ExtractNickname(request);
        return rankingService.myRanking(result);
    }

    /*@GetMapping("/top/{category}")
    public ResponseEntity<ResponseRakingList> getRanking(@PathVariable("category") PostCategory category){

        List<Rating> topRating = rankingService.getTopRating(category);

        return new ResponseEntity<>(new ResponseRakingList(topRating), HttpStatus.OK);
    }*/
}
