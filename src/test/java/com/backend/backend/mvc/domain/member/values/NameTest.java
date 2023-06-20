package com.backend.backend.mvc.domain.member.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    @DisplayName("Null name value throws IllegalArgumentException")
    void from_null_name_throws_exception() {
        String nullName = null;

        assertThrows(IllegalArgumentException.class, () -> Name.from(nullName));
    }

    @Test
    @DisplayName("Name value exceeding 4 characters throws IllegalArgumentException")
    void from_name_exceeds_length_throws_exception() {
        String longName = "a".repeat(5);

        assertThrows(IllegalArgumentException.class, () -> Name.from(longName));
    }

    @Test
    @DisplayName("Invalid name format throws IllegalArgumentException")
    void from_invalid_name_format_throws_exception() {
        String invalidName = "123a";

        assertThrows(IllegalArgumentException.class, () -> Name.from(invalidName));
    }

    @Test
    @DisplayName("Valid name returns Name object")
    void from_valid_name_returns_name() {
        String validName = "가나다";

        Name name = Name.from(validName);

        assertNotNull(name);
        assertEquals(validName, name.getName());
    }

    @Test
    @DisplayName("Name toString returns string representation of name")
    void to_string_returns_string_representation_of_name() {
        String validName = "가나다";

        Name name = Name.from(validName);

        assertEquals(validName, name.toString());
    }
}