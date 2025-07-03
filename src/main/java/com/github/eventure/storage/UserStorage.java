package com.github.eventure.storage;

import java.util.List;
import java.util.Optional;

import com.github.eventure.model.User;

public class UserStorage extends Storage<User> {
    private static final UserStorage instance = new UserStorage();

    
    private UserStorage() {
        super();
    }

    
    public static UserStorage getInstance() {
        return instance;
    }

    
    public List<User> getUsers() {
        return this;  
    }

   
    public Optional<User> findUserByUsername(String username) {
        return find(user -> user.getUsername().equals(username)).findFirst();
    }
}
