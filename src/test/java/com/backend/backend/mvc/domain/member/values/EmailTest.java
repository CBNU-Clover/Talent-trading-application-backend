package com.backend.backend.mvc.domain.member.values;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    @Test
    @DisplayName("Null name value throws IllegalArgumentException")
    void from_null_email_throws_exception() {
        String nullEmail = null;

        assertThrows(IllegalArgumentException.class, () -> Email.from(nullEmail));
    }



    @Test
    @DisplayName("Invalid email format throws IllegalArgumentException")
    void from_invalid_name_format_throws_exception() {
        String invalidName = "123a.ht";

        assertThrows(IllegalArgumentException.class, () -> Name.from(invalidName));
    }

    @Test
    @DisplayName("Valid email returns Email object")
    void from_valid_email_returns_nickname() {
        String validEmail = "abc@email.com";

        Email email = Email.from(validEmail);

        assertNotNull(email);
        assertEquals(validEmail, email.getEmail());
    }

    @Test
    @DisplayName("Email toString returns string representation of email")
    void to_string_returns_string_representation_of_email() {
        String validEmail = "abc@email.com";

        Email email = Email.from(validEmail);

        assertEquals(validEmail, email.toString());
    }
}