package com.backend.backend.controller.ranking.dto;

import com.backend.backend.domain.member.Rating;
import lombok.Data;
import lombok.Getter;
import lombok.Lombok;

@Data
public class ResponseRaking {

    private String nickname;
    private Long score;

    public ResponseRaking(Rating rating) {
        this.nickname = rating.getMember().getNickname();
        this.score = rating.getScore();
    }
}
