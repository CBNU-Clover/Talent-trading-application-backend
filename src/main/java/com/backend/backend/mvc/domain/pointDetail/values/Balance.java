package com.backend.backend.mvc.domain.pointDetail.values;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Balance {

    @Column(name = "balance")
    private Long amount;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 외부 패키지에서 호출하면 안됨
     */
    protected Balance() {

    }

    private Balance(Long amount) {
        validateCheck(amount);
        this.amount = amount;
    }

    private void validateCheck(Long balance) {
        if (balance == null) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("값이 0보다 커야 합니다");
        }
    }

    public static Balance from(Long balance) {
        return new Balance(balance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return Objects.equals(this.amount, balance.amount);
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
