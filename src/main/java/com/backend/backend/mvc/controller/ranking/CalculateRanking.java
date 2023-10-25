package com.backend.backend.mvc.controller.ranking;

public class CalculateRanking {

    public String calculateRanking(Long score) {
        if (score >= 50) {
            return "S";
        } else if ((40 <= score) && (score < 50)) {
            return "A+";
        } else if ((30 <= score) && (score < 40)) {
            return "A";
        } else if ((20 <= score) && (score < 30)) {
            return "B+";
        } else if (score < 20) {
            return "B";
        }
        return "등급 오류";
    }
    public CalculateRanking()
    {

    }
}
