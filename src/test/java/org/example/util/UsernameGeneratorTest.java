package org.example.util;

import org.example.model.User;
import org.example.repository.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class UsernameGeneratorTest {

    @InjectMocks
    private UsernameGenerator usernameGenerator;

    @Mock
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generateUsernameTest() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userDAO.isUsernameExists("John.Doe")).thenReturn(false);

        String username = usernameGenerator.generateUsername(user);

        assertEquals("John.Doe", username);
    }

    @Test
    public void generateWhenUsernameExistsTest() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userDAO.isUsernameExists("John.Doe")).thenReturn(true);
        when(userDAO.isUsernameExists("John.Doe1")).thenReturn(false);

        String username = usernameGenerator.generateUsername(user);

        assertEquals("John.Doe1", username);
    }

    @Test
    public void generateUsernameForNullUserTest() {
        assertThrows(NullPointerException.class, () -> usernameGenerator.generateUsername(null));
    }

    @Test
    public void generateUsernameForEmptyNameTest() {
        User user = new User();
        user.setFirstName("");
        user.setLastName("");
        String username = usernameGenerator.generateUsername(user);
        assertEquals(".", username);
    }
}