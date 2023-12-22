package org.example.repository;


import org.example.model.User;
import org.example.storage.InMemoryStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class UserDAOTest {

    private UserDAO userDAO;

    @Mock
    private InMemoryStorage storage;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userDAO = new UserDAO(storage);
    }

    @Test
    public void isUsernameExistsTest() {
        User user = new User();
        user.setUsername("testUsername");

        Map<Integer, Object> users = new HashMap<>();
        users.put(1, user);

        when(storage.getFromStorageAllData("users")).thenReturn(users);

        assertTrue(userDAO.isUsernameExists("testUsername"));
        assertFalse(userDAO.isUsernameExists("nonexistentUsername"));
    }

    @Test
    public void isUsernameExistsWhenUsersNamespaceIsEmptyTest() {
        when(storage.getFromStorageAllData("users")).thenReturn(Collections.emptyMap());

        assertFalse(userDAO.isUsernameExists("testUsername"));
    }
}