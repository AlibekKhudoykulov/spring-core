package org.example.repository;

import org.example.contant.EntityType;
import org.example.model.User;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDAO {
    private final InMemoryStorage storage;

    public UserDAO(InMemoryStorage storage) {
        this.storage = storage;
    }

    public boolean isUsernameExists(String username) {
        Map<Integer, Object> usersNamespace = storage.getFromStorageAllData(EntityType.USER);

        for (Object entity : usersNamespace.values()) {
            if (entity instanceof User user) {
                if (user.getUsername() != null && user.getUsername().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }
}