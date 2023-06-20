package com.backend.backend.mvc.domain.post.values;

import com.backend.backend.mvc.domain.pointDetail.values.Balance;

import javax.persistence.Column;

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

}
