package org.example.util;

import org.example.model.User;
import org.example.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsernameGenerator {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String generateUsername(User user) {
        String baseUsername = user.getFirstName() + "." + user.getUsername();
        String username = baseUsername;
        int count = 1;

        // Check and append a serial number if the username exists
        while (userDAO.isUsernameExists(username)) {
            username = baseUsername + count;
            count++;
        }
        return username;
    }
}
