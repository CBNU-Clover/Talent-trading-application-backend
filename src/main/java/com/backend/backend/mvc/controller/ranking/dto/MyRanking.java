package com.backend.backend.mvc.controller.ranking.dto;

import com.backend.backend.mvc.domain.rating.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data

public class MyRanking {

    String nickname;
    Long score;
    String grade;
    public MyRanking(Rating rating,String grade)
    {
        this.nickname=rating.getMember().getNickname().toString();
        this.score=rating.getScore();
        this.grade=grade;
    }
}
