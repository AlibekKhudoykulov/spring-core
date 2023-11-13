package org.example.repository;

import org.example.model.User;
import org.example.storage.InMemoryStorage;
import org.springframework.stereotype.Repository;

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
}