package com.backend.backend.mvc.domain.pointDetail.values;

import com.backend.backend.mvc.domain.member.values.Name;
import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Recipient {

    private String name;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 member 외부 패키지에서 호출하면 안됨
     */
    protected Recipient() {

    }

    private Recipient(String name) {
        validateCheck(name);
        this.name = name;
    }

    private void validateCheck(String name) {
        if (name == null) {
            throw new IllegalArgumentException("수신처를 입력해주세요.");
        }
    }

    public static Recipient from(String name) {
        return new Recipient(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(name, recipient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
