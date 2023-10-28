package com.backend.backend.mvc.controller.ranking;

public class CalculateRanking {

    public String calculateRanking(Long score) {
        if (score >= 10000) {
            return "S";
        } else if (score>=5000) {
            return "A+";
        } else if (score>=1000) {
            return "A";
        } else if (score>=500) {
            return "B+";
        } else {
            return "B";
        }
    }
    public CalculateRanking()
    {

    }
}
