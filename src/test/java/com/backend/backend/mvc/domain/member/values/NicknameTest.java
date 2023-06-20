package com.backend.backend.mvc.domain.member.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * best unit test
 */
class NicknameTest {
    @Test
    @DisplayName("Null nickname value throws IllegalArgumentException")
    void from_null_nickname_throws_exception() {
        String nullNickname = null;

        assertThrows(IllegalArgumentException.class, () -> Nickname.from(nullNickname));
    }

    @Test
    @DisplayName("Nickname value exceeding 39 characters throws IllegalArgumentException")
    void from_nickname_exceeds_length_throws_exception() {
        String longNickname = "a".repeat(40);

        assertThrows(IllegalArgumentException.class, () -> Nickname.from(longNickname));
    }

    @Test
    @DisplayName("Invalid nickname format throws IllegalArgumentException")
    void from_invalid_nickname_format_throws_exception() {
        String invalidNickname = "123";

        assertThrows(IllegalArgumentException.class, () -> Nickname.from(invalidNickname));
    }

    @Test
    @DisplayName("Valid nickname returns Nickname object")
    void from_valid_nickname_returns_nickname() {
        String validNickname = "validNickname";

        Nickname nickname = Nickname.from(validNickname);

        assertNotNull(nickname);
        assertEquals(validNickname, nickname.getNickname());
    }

    @Test
    @DisplayName("Nickname toString returns string representation of nickname")
    void to_string_returns_string_representation_of_nickname() {
        String validNickname = "validNickname";

        Nickname nickname = Nickname.from(validNickname);

        assertEquals(validNickname, nickname.toString());
    }
}