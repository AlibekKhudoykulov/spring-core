package org.example.util;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class PasswordGenerator {

    public String generateRandomPassword() {
        // Logic to generate a random password
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        while (password.length() < 10) {
            int index = (int) (rnd.nextFloat() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}
