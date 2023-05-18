package com.backend.backend.controller.ranking;

import com.backend.backend.controller.post.Dto.PostReadResponse;
import com.backend.backend.controller.ranking.dto.ResponseRakingList;
import com.backend.backend.domain.member.Rating;
import com.backend.backend.domain.member.RatingCategory;
import com.backend.backend.domain.post.Post;
import com.backend.backend.service.RakingService;
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
    private final RakingService rakingService;

    @GetMapping("/top/{category}")
    public ResponseEntity<ResponseRakingList> readPost(@PathVariable("category") RatingCategory category){

        List<Rating> topRating = rakingService.getTopRating(category);

        return new ResponseEntity<>(new ResponseRakingList(topRating), HttpStatus.OK);
    }
}
