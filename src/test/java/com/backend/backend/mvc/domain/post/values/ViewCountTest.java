package com.backend.backend.mvc.domain.post.values;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewCountTest {

    ViewCount viewCount;
    @BeforeEach
    void setUp() {
        viewCount = ViewCount.from();
    }

    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        Long nullData = null;

        assertThrows(IllegalArgumentException.class, () -> viewCount.addCount(nullData));
    }

    @Test
    @DisplayName("Invalid format throws IllegalArgumentException")
    void from_invalid_format_throws_exception() {
        Long invalidValue = -10L;

        assertThrows(IllegalArgumentException.class, () -> viewCount.addCount(invalidValue));
    }

    @Test
    @DisplayName("Valid data")
    void from_valid_returns() {
        Long validValue = 10L;

        viewCount.addCount(validValue);

        assertNotNull(viewCount);
        assertEquals(validValue, viewCount.getCount());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        Long validValue = 10L;

        viewCount.addCount(validValue);

        assertEquals(validValue.toString(), viewCount.toString());
    }
}