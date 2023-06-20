package com.backend.backend.mvc.domain.post.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {
    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        String nullData = null;

        assertThrows(IllegalArgumentException.class, () -> Content.from(nullData));
    }

    @Test
    @DisplayName("Valid return")
    void from_valid_returns() {
        String validValue = "test";

        Content content = Content.from(validValue);

        assertNotNull(content);
        assertEquals(validValue, content.toString());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        String validValue = "test";

        Content validObject = Content.from(validValue);

        assertEquals(validValue, validObject.toString());
    }
}