package com.github.eventure.controllers;

import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.User;

public class UserController {
    public User createUser(String firstName, String lastName, String password) {
        // Instantiate the user
        var u = new User();
        u.setName(firstName + " " + lastName);

        // Create the password hash
        var salt = Encryption.generateSalt();
        var hash = Encryption.generateHash(password, salt);
        u.setPasswordSalt(salt);
        u.setPasswordHash(hash);

        // Return the user
        return u;
    }
}
