package com.backend.backend.mvc.domain.post.values;

import com.backend.backend.mvc.domain.pointDetail.values.Balance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {
    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        Long nullData = null;

        assertThrows(IllegalArgumentException.class, () -> Price.from(nullData));
    }

    @Test
    @DisplayName("Invalid format throws IllegalArgumentException")
    void from_invalid_format_throws_exception() {
        Long invalidValue = -10L;

        assertThrows(IllegalArgumentException.class, () -> Price.from(invalidValue));
    }

    @Test
    @DisplayName("Valid return")
    void from_valid_returns() {
        Long validValue = 10L;

        Price price = Price.from(validValue);

        assertNotNull(price);
        assertEquals(validValue, price.getAmount());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        Long validValue = 10L;

        Price validObject = Price.from(validValue);

        assertEquals(validValue.toString(), validObject.toString());
    }
}