package com.backend.backend.mvc.controller.ranking.dto;

import com.backend.backend.mvc.domain.rating.Rating;
import lombok.Data;

@Data
public class ResponseRaking {

    private String nickname;
    private Long score;

    public ResponseRaking(Rating rating) {
        this.nickname = rating.getMember().getNickname().toString();
        this.score = rating.getScore();
    }
}
