package com.backend.backend.mvc.domain.pointDetail.values;

import org.hibernate.engine.jdbc.batch.spi.BatchKey;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceTest {
    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        Long nullData = null;

        assertThrows(IllegalArgumentException.class, () -> Balance.from(nullData));
    }

    @Test
    @DisplayName("Invalid format throws IllegalArgumentException")
    void from_invalid_format_throws_exception() {
        Long invalidValue = -10L;

        assertThrows(IllegalArgumentException.class, () -> Balance.from(invalidValue));
    }

    @Test
    @DisplayName("Valid return")
    void from_valid_returns() {
        Long validValue = 10L;

        Balance balance = Balance.from(validValue);

        assertNotNull(balance);
        assertEquals(validValue, balance.getAmount());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        Long validValue = 10L;

        Balance validObject = Balance.from(validValue);

        assertEquals(validValue.toString(), validObject.toString());
    }
}