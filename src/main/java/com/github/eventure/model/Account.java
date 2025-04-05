package com.github.eventure.model;

import com.github.eventure.model.passwords.Password;

public class Account {
    private Person accountOwner;
    private Password accountPassword;

    public Account() {}

    public Person getAccountOwner() {
        return accountOwner;
    }

    public Password getAccountPassword() {
        return accountPassword;
    }
}
