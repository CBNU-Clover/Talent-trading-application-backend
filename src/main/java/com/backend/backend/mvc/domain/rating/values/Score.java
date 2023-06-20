package com.backend.backend.mvc.domain.rating.values;

import javax.persistence.Column;
import java.util.Objects;

public class Score {
    @Column(name = "score")
    private Long amount;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 외부 패키지에서 호출하면 안됨
     */
    protected Score() {

    }


    private void validateCheck(Long amount) {
        if (amount == null) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("값이 0보다 커야 합니다");
        }
    }

    public static Score from() {
        return new Score();
    }

    public void addScore(Long point) {
        validateCheck(point);
        this.amount += point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(amount, score.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return amount.toString();
    }
}
