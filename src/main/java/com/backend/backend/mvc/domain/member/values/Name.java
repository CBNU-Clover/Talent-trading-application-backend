package com.backend.backend.mvc.domain.member.values;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Getter
public class Name {

    /**
     * 최소한개의 한글 문자에서 최대 4개의 한글 문자호 이루어 져야 한다
     */
    private static final Pattern pattern = Pattern.compile("^[가-힣]{2,4}$");

    private String name;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 member 외부 패키지에서 호출하면 안됨
     */
    protected Name() {

    }

    private Name(String name) {
        validateNickname(name);
        this.name = name;
    }

    private void validateNickname(String name) {
        if (name == null) {
            throw new IllegalArgumentException("이름을 입력해주세요.");
        }
        if (name.length() > 4) {
            throw new IllegalArgumentException("입력 가능한 이름의 최대길이를 초과했습니다.");
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException("입력 가능한 이름의 최소길이 미만입니다.");
        }
        if (!pattern.matcher(name).matches()) {
            throw new IllegalArgumentException("올바른 양식의 닉네임을 입력해주세요.");
        }
    }

    public static Name from(String name) {
        return new Name(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Name name)) return false;
        return getName().equals(name.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
