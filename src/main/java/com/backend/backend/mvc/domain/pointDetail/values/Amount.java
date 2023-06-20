package com.backend.backend.mvc.domain.pointDetail.values;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

/**
 * 금액을 나타내는 값객체
 */
@Embeddable
@Getter
public class Amount {
    private Long amount;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 외부 패키지에서 호출하면 안됨
     */
    protected Amount() {

    }

    private Amount(Long amount) {
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

    public static Amount from(Long amount) {
        return new Amount(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount1 = (Amount) o;
        return Objects.equals(amount, amount1.amount);
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
