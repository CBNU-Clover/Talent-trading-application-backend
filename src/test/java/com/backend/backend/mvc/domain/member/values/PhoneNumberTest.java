package com.backend.backend.mvc.domain.member.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {
    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        String nullData = null;

        assertThrows(IllegalArgumentException.class, () -> PhoneNumber.from(nullData));
    }

    @Test
    @DisplayName("value exceeding 11 characters throws IllegalArgumentException")
    void from_exceeds_length_throws_exception() {
        String longValue = "0".repeat(12);

        assertThrows(IllegalArgumentException.class, () -> PhoneNumber.from(longValue));
    }

    @Test
    @DisplayName("Invalid format throws IllegalArgumentException")
    void from_invalid_format_throws_exception() {
        String invalidValue = "010-234-5678";

        assertThrows(IllegalArgumentException.class, () -> PhoneNumber.from(invalidValue));
    }

    @Test
    @DisplayName("Valid return")
    void from_valid_returns() {
        String validValue = "01012345678";

        PhoneNumber phoneNumber = PhoneNumber.from(validValue);

        assertNotNull(phoneNumber);
        assertEquals(validValue, phoneNumber.getPhoneNumber());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        String validValue = "01012345678";

        PhoneNumber validObject = PhoneNumber.from(validValue);

        assertEquals(validValue, validObject.toString());
    }
}