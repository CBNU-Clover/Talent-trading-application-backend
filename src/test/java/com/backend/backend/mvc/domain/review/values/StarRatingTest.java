package com.backend.backend.mvc.domain.review.values;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StarRatingTest {

    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        Long nullData = null;

        assertThrows(IllegalArgumentException.class, () -> StarRating.from(nullData));
    }

    @Test
    @DisplayName("Invalid format throws IllegalArgumentException")
    void from_invalid_format_throws_exception() {
        Long invalidValue = -1L;

        assertThrows(IllegalArgumentException.class, () -> StarRating.from(invalidValue));
    }

    @Test
    @DisplayName("Invalid format throws IllegalArgumentException")
    void from_invalid_over_format_throws_exception() {
        Long invalidValue = 6L;

        assertThrows(IllegalArgumentException.class, () -> StarRating.from(invalidValue));
    }

    @Test
    @DisplayName("Valid data")
    void from_valid_returns() {
        Long validValue = 5L;

        assertEquals(validValue, StarRating.from(validValue).getRating());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        Long validValue = 3L;

        assertEquals(validValue.toString(), StarRating.from(validValue).toString());
    }

}