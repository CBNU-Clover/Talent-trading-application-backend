package com.backend.backend.mvc.domain.member.values;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
@Getter
public class PhoneNumber {

    private static final Pattern pattern = Pattern.compile("^010\\d{7,8}$");

    private String phoneNumber;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber) {
        validateEmail(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void validateEmail(String phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalArgumentException("전화번호를 입력해주세요.");
        }
        if (!pattern.matcher(phoneNumber).matches()) {
            throw new IllegalArgumentException("올바른 양식의 전화번호를 입력해주세요.");
        }
    }

    public static PhoneNumber from(String phoneNumber) {
        return new PhoneNumber(phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return phoneNumber;
    }
}
