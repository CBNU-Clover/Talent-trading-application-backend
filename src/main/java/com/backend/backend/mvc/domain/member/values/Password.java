package com.backend.backend.mvc.domain.member.values;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Getter
public class Password {

    private static final Pattern pattern = Pattern.compile("^010\\d{7,8}$");


    /**
     * @Nullary-Constructor. JPA 기본 생성자로 member 외부 패키지에서 호출하지 말 것.
     */
    private String password;

    public Password() {
    }

    public Password(String password) {
        validateCheck(password);
        this.password = password;
    }

    private void validateCheck(String password) {
        if (password == null) {
            throw new IllegalArgumentException("비밀번호를 입력해주세요.");
        }

//        if (!pattern.matcher(password).matches()) {
//            throw new IllegalArgumentException("올바른 양식의 비밀번호를 입력해주세요.");
//        }
    }

    public static Password from(String password) {
        return new Password(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

    @Override
    public String toString() {
        return password;
    }
}
