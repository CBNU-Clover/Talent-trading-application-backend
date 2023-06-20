package com.backend.backend.mvc.domain.pointDetail.values;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
public class Sender {

    @Column(name = "sender")
    private String name;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 외부 패키지에서 호출하면 안됨
     */
    protected Sender() {

    }

    private Sender(String name) {
        validateCheck(name);
        this.name = name;
    }

    private void validateCheck(String name) {
        if (name == null) {
            throw new IllegalArgumentException("발신처를 입력해주세요.");
        }
    }

    public static Sender from(String name) {
        return new Sender(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sender sender = (Sender) o;
        return Objects.equals(name, sender.name);
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
