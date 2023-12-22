package org.example.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordGeneratorTest {

    @InjectMocks
    private PasswordGenerator passwordGenerator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void generateRandomPasswordTest() {
        String password = passwordGenerator.generateRandomPassword();

        assertNotNull(password, "The generated password cannot be null");
        assertEquals(10, password.length(), "The length of the password should be 10");

        // check if the password contains only the allowed characters
        assertTrue(password.matches("[A-Za-z0-9]{10}"), "Password contains illegal characters");
    }
}