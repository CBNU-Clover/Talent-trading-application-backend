package com.backend.backend.controller.ranking.dto;

import com.backend.backend.domain.rating.Rating;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseRakingList {
    List<ResponseRaking> rankings;

    public ResponseRakingList(List<Rating> rankings) {
        rankings = new ArrayList<>();
        for(Rating rating: rankings){
            this.rankings.add(new ResponseRaking(rating));
        }
    }
}
