package com.backend.backend.mvc.domain.pointDetail.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SenderTest {
    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        String nullData = null;

        assertThrows(IllegalArgumentException.class, () -> Sender.from(nullData));
    }

    @Test
    @DisplayName("Valid return")
    void from_valid_returns() {
        String validValue = "test";

        Sender sender = Sender.from(validValue);

        assertNotNull(sender);
        assertEquals(validValue, sender.toString());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        String validValue = "test";

        Sender validObject = Sender.from(validValue);

        assertEquals(validValue, validObject.toString());
    }
}