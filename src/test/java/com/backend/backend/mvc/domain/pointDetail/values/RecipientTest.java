package com.backend.backend.mvc.domain.pointDetail.values;

import com.backend.backend.mvc.domain.member.values.Password;
import com.backend.backend.mvc.domain.member.values.PhoneNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipientTest {
    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        String nullData = null;

        assertThrows(IllegalArgumentException.class, () -> Recipient.from(nullData));
    }

    @Test
    @DisplayName("Valid return")
    void from_valid_returns() {
        String validValue = "test";

        Recipient recipient = Recipient.from(validValue);

        assertNotNull(recipient);
        assertEquals(validValue, recipient.toString());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        String validValue = "test";

        Recipient validObject = Recipient.from(validValue);

        assertEquals(validValue, validObject.toString());
    }
}