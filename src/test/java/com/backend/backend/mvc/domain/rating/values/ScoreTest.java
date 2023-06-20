package com.backend.backend.mvc.domain.rating.values;

import com.backend.backend.mvc.domain.post.values.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    Score score;
    @BeforeEach
    void setUp() {
        score = Score.from();
    }

    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        Long nullData = null;

        assertThrows(IllegalArgumentException.class, () -> score.addScore(nullData));
    }

    @Test
    @DisplayName("Invalid format throws IllegalArgumentException")
    void from_invalid_format_throws_exception() {
        Long invalidValue = -10L;

        assertThrows(IllegalArgumentException.class, () -> score.addScore(invalidValue));
    }

    @Test
    @DisplayName("Valid data")
    void from_valid_returns() {
        Long validValue = 10L;

        score.addScore(validValue);

        assertNotNull(score);
        assertEquals(validValue, score.getAmount());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        Long validValue = 10L;

        score.addScore(validValue);

        assertEquals(validValue.toString(), score.toString());
    }
}