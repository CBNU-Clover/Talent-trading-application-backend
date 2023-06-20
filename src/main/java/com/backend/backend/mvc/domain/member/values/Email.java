package com.backend.backend.mvc.domain.member.values;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Getter
public class Email {
    /**
     * 최소한개의 한글 문자에서 최대 4개의 한글 문자호 이루어 져야 한다
     */
    private static final Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private String email;


    /**
     * @Nullary-Constructor JPA 기본 생성자로 member 외부 패키지에서 호출하면 안됨
     */
    public Email() {
    }

    public Email(String email) {
        validateEmail(email);
        this.email = email;
    }

    private void validateEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("이름을 입력해주세요.");
        }
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("올바른 양식의 이메일을 입력해주세요.");
        }
    }

    public static Email from(String email) {
        return new Email(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return Objects.equals(email, email1.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return email;
    }
}
