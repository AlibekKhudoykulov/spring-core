package org.example.repository;

import org.example.constant.EntityType;
import org.example.model.User;
import org.example.model.template.BaseEntity;
import org.example.storage.InMemoryStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDAO {
    private InMemoryStorage storage;

    @Autowired
    public void setStorage(InMemoryStorage storage) {
        this.storage = storage;
    }

    public boolean isUsernameExists(String username) {
        Map<Integer, BaseEntity> usersNamespace = storage.getFromStorageAllData(EntityType.USER);

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