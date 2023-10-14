package com.backend.backend.mvc.controller.ranking.dto;

import com.backend.backend.mvc.domain.rating.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseRankingList {

    private String nickname;
    private Long score;

    private String rank;
}