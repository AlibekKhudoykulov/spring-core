package org.example.repository;

import org.example.model.User;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class UserDAO {
    private InMemoryStorage storage;

    public UserDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    public void createUser(User user) {
        storage.addToStorage("users", user.getId(), user);
    }

    public void updateUser(User user) {
        storage.updateStorage("users", user.getId(), user);
    }

    public User getUser(int userId) {
        return (User) storage.getFromStorage("users", userId);
    }
    public boolean isUsernameExists(String username) {
        int entityId = 0;
        Object entity = storage.getFromStorage("users", entityId);

        while (entity != null) {
            if (entity instanceof User) {
                User user = (User) entity;
                if (user.getUsername() != null && user.getUsername().equals(username)) {
                    return true;
                }
            }
            entityId++;
            entity = storage.getFromStorage("users", entityId);
        }
        return false;
    }
}