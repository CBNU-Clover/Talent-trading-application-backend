package com.backend.backend.mvc.domain.member.values;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Getter
public class Nickname {

    /**
     * 최소한개의 한글 문자 또는 알파벳이 존재하여야 하고 문자열의 길이는 1~39이다
     */
    private static final Pattern pattern = Pattern.compile("^(?=.*[\\p{IsHangul}\\p{IsAlphabetic}]).{1,39}$");

    private String nickname;

    /**
     * @Nullary-Constructor JPA 기본 생성자로 member 외부 패키지에서 호출하면 안됨
     */
    protected Nickname() {

    }

    private Nickname(String nickname) {
        validateNickname(nickname);
        this.nickname = nickname;
    }

    private void validateNickname(String nickname) {
        if (nickname == null) {
            throw new IllegalArgumentException("닉네임을 입력해주세요.");
        }
        if (nickname.length() > 39) {
            throw new IllegalArgumentException("입력 가능한 닉네임의 최대길이를 초과했습니다.");
        }
        if (nickname.length() < 1) {
            throw new IllegalArgumentException("입력 가능한 닉네임의 최소길이 미만입니다.");
        }
        if (!pattern.matcher(nickname).matches()) {
            throw new IllegalArgumentException("올바른 양식의 닉네임을 입력해주세요.");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nickname nickName)) return false;
        return getNickname().equals(nickName.getNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickname());
    }

    @Override
    public String toString() {
        return nickname;
    }
}