package com.github.eventure.model;

public class Session {
    private static User loggedUser = null;

    public static void login(User user) {
        loggedUser = user;
    }

    public static void logout() {
        loggedUser = null;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static boolean isLoggedIn() {
        return loggedUser != null;
    }
}
