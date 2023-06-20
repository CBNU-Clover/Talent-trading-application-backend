package com.backend.backend.mvc.domain.post.values;

import com.backend.backend.mvc.domain.pointDetail.values.Recipient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostNameTest {
    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        String nullData = null;

        assertThrows(IllegalArgumentException.class, () -> PostName.from(nullData));
    }

    @Test
    @DisplayName("Valid return")
    void from_valid_returns() {
        String validValue = "test";

        PostName postName = PostName.from(validValue);

        assertNotNull(postName);
        assertEquals(validValue, postName.toString());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        String validValue = "test";

        PostName validObject = PostName.from(validValue);

        assertEquals(validValue, validObject.toString());
    }
}