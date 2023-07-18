package com.backend.backend.mvc.domain.review.values;

import com.backend.backend.mvc.domain.rating.values.Score;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class StarRating {
    
    private Long rating=0L;

    public StarRating() {
    }

    public StarRating(Long rating) {
        validateCheck(rating);
        this.rating = rating;
    }

    private void validateCheck(Long rating) {
        if (rating == null) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
        if (rating < 0) {
            throw new IllegalArgumentException("값이 0보다 커야 합니다");
        }
        if (rating > 5) {
            throw new IllegalArgumentException("값이 5보다 작아야 합니다");
        }
    }

    public static StarRating from(Long rating) {
        return new StarRating(rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StarRating that = (StarRating) o;
        return Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating);
    }

    @Override
    public String toString() {
        return rating.toString();
    }
}
