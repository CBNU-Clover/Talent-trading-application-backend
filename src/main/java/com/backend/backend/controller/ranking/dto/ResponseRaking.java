package com.backend.backend.controller.ranking.dto;

import com.backend.backend.domain.rating.Rating;
import lombok.Data;

@Data
public class ResponseRaking {

    private String nickname;
    private Long score;

    public ResponseRaking(Rating rating) {
        this.nickname = rating.getMember().getNickname();
        this.score = rating.getScore();
    }
}
