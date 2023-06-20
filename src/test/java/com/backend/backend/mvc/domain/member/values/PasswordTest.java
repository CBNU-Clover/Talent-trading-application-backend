package com.backend.backend.mvc.domain.member.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {
    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        String nullData = null;

        assertThrows(IllegalArgumentException.class, () -> Password.from(nullData));
    }

//    @Test
//    @DisplayName("value exceeding 11 characters throws IllegalArgumentException")
//    void from_exceeds_length_throws_exception() {
//        String longValue = "0".repeat(12);
//
//        assertThrows(IllegalArgumentException.class, () -> Password.from(longValue));
//    }

//    @Test
//    @DisplayName("Invalid format throws IllegalArgumentException")
//    void from_invalid_format_throws_exception() {
//        String invalidValue = "가,dbh";
//
//        assertThrows(IllegalArgumentException.class, () -> Password.from(invalidValue));
//    }
//
//    @Test
//    @DisplayName("Valid return")
//    void from_valid_returns() {
//        String validValue = "abc1234+";
//
//        Password password = Password.from(validValue);
//
//        assertNotNull(password);
//        assertEquals(validValue, password.getPassword());
//    }
//
//    @Test
//    @DisplayName("toString returns string representation")
//    void to_string_returns_string_representation() {
//        String validValue = "abc1234+";
//
//        Password validObject = Password.from(validValue);
//
//        assertEquals(validValue, validObject.toString());
//    }
}