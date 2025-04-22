package com.github.eventure.view.pages;

import javax.swing.*;
import java.util.Optional;

import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.User;
import com.github.eventure.storage.UserStorage;
import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

public class LoginPage extends Page {

    public LoginPage() {
        super(PageLayouts.REGULAR_LAYOUT);

        UserStorage userStorage = new UserStorage();  // Instancia o UserStorage

        add(new JLabel("LOGIN"), "center, wrap");

        // Campos de entrada para login
        var loginField = new JTextField();
        var passwordField = new JPasswordField();

        add(new JLabel("Login:"), "split 2");
        add(loginField, "growx, wrap");

        add(new JLabel("Senha:"), "split 2");
        add(passwordField, "growx, wrap");

        var loginBtn = new JButton("Entrar");
        loginBtn.addActionListener(e -> {
            String login = loginField.getText().trim();  // Remove espaços extras
            String password = new String(passwordField.getPassword()).trim();

            if (login.isEmpty() || password.isEmpty()) {
                sendNotification(new com.github.eventure.view.windowing.basic.Notification("Todos os campos devem ser preenchidos!"));
            } else {
                // Buscando usuário com login (email, nome de usuário ou nome completo)
                Optional<User> userFound = userStorage.stream()
                        .filter(u -> u.getEmail().equalsIgnoreCase(login) || 
                                    u.getUserName().equalsIgnoreCase(login) || 
                                    u.getName().equalsIgnoreCase(login))
                        .findFirst();  // Encontra o primeiro usuário correspondente

                if (userFound.isPresent()) {
                    User foundUser = userFound.get();
                    byte[] expectedHash = foundUser.getPasswordHash();
                    byte[] salt = foundUser.getPasswordSalt();
                    byte[] hashToCheck = Encryption.generateHash(password, salt);

                    if (Encryption.checkHashes(expectedHash, hashToCheck)) {
                        sendNotification(new com.github.eventure.view.windowing.basic.Notification("Login realizado com sucesso!"));
                        switchPage(getPageId(SecondaryPage.class));  // Navega para a próxima página
                    } else {
                        sendNotification(new com.github.eventure.view.windowing.basic.Notification("Login ou senha incorretos!"));
                    }
                } else {
                    sendNotification(new com.github.eventure.view.windowing.basic.Notification("Usuário não encontrado!"));
                }
            }
        });

        add(loginBtn, "span, center, wrap");
    }
}
