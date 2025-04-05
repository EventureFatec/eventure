package com.github.eventure.model;

public class Session {
    public static Account loggedInAccount;

    public static boolean login(Account a) {
        if (loggedInAccount == null) {
            loggedInAccount = a;
            return true;
        }
        return false;
    }

    public static boolean logout() {
        if (loggedInAccount != null) {
            loggedInAccount = null;
            return true;
        } else {
            return false;
        }
    }

    public static Account getLoggedInUser() {
        return loggedInAccount;
    }
}
