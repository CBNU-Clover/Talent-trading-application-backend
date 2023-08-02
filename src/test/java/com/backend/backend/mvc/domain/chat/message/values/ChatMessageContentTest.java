package com.backend.backend.mvc.domain.chat.message.values;

import com.backend.backend.mvc.domain.post.values.Content;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatMessageContentTest {

    @Test
    @DisplayName("Null value throws IllegalArgumentException")
    void from_null_throws_exception() {
        String nullData = null;

        assertThrows(IllegalArgumentException.class, () -> MessageContent.from(nullData));
    }

    @Test
    @DisplayName("blink throws IllegalArgumentException")
    void from_blink_throws_exception() {
        String blinkData = "";

        assertThrows(IllegalArgumentException.class, () -> MessageContent.from(blinkData));
    }

    @Test
    @DisplayName("blink throws IllegalArgumentException")
    void from_blink_throws_exception2() {
        String blinkData = " ";

        assertThrows(IllegalArgumentException.class, () -> MessageContent.from(blinkData));
    }


    @Test
    @DisplayName("Valid return")
    void from_valid_returns() {
        String validValue = "test";

        MessageContent content = MessageContent.from(validValue);

        assertNotNull(content);
        assertEquals(validValue, content.toString());
    }

    @Test
    @DisplayName("toString returns string representation")
    void to_string_returns_string_representation() {
        String validValue = "test";

        MessageContent validObject = MessageContent.from(validValue);

        assertEquals(validValue, validObject.toString());
    }
}