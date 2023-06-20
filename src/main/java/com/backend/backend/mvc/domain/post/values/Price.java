package com.backend.backend.mvc.domain.post.values;

import com.backend.backend.mvc.domain.pointDetail.values.Balance;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Price {
    @Column(name = "price")
    private Long amount;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 외부 패키지에서 호출하면 안됨
     */
    protected Price() {

    }

    private Price(Long amount) {
        validateCheck(amount);
        this.amount = amount;
    }

    private void validateCheck(Long amount) {
        if (amount == null) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("값이 0보다 커야 합니다");
        }
    }

    public static Price from(Long amount) {
        return new Price(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(amount, price.amount);
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
