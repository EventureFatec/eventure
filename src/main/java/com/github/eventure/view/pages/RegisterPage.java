package com.github.eventure.view.pages;

import javax.swing.*;

import com.github.eventure.controllers.EmailController;
import com.github.eventure.controllers.PasswordController;
import com.github.eventure.encryption.Encryption;
import com.github.eventure.model.User;
import com.github.eventure.model.passwords.PasswordRules;
import com.github.eventure.storage.UserStorage;
import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

public class RegisterPage extends Page {

    public RegisterPage() {
        super(PageLayouts.REGULAR_LAYOUT);

        add(new JLabel("Cadastro de Usuário"), "center, wrap");

        // Campos de entrada para dados do usuário
        var nameField = new JTextField();
        var userField = new JTextField();
        var emailField = new JTextField();
        var passwordField = new JPasswordField();
        var confirmPasswordField = new JPasswordField();

        add(new JLabel("Nome Completo:"), "split 2");
        add(nameField, "growx, wrap");

        add(new JLabel("Nome de Usuário:"), "split 2");
        add(userField, "growx, wrap");

        add(new JLabel("Senha:"), "split 4");
        add(passwordField, "growx");

        add(new JLabel("Confirmar Senha:"), "split 2");
        add(confirmPasswordField, "growx, wrap");

        add(new JLabel("Email:"), "split 2");
        add(emailField, "growx, wrap");

        var registerBtn = new JButton("Cadastrar");
        registerBtn.addActionListener(e -> {
            UserStorage userStorage = new UserStorage();
            EmailController emailController = new EmailController();
            PasswordController passwordController = new PasswordController();

            String name = nameField.getText();
            String user = userField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String email = emailField.getText();

            boolean emailIsValid = emailController.ValidateEmail(email);
            short flags = passwordController.validatePasswordRules(password);

            if (name.isEmpty() || user.isEmpty() || password.isEmpty() || email.isEmpty()) {
                sendNotification(new com.github.eventure.view.windowing.basic.Notification("Todos os campos são obrigatórios!"));
            } else if (!password.equals(confirmPassword)) {
                sendNotification(new com.github.eventure.view.windowing.basic.Notification("As senhas devem ser iguais!"));
            } else if (!isPasswordValid(flags)) {
                sendNotification((new com.github.eventure.view.windowing.basic.Notification("A senha deve conter pelo menos 8 caracteres!")));
                } else if (emailIsValid) {
                // Criação de um novo usuário
                User userObj = new User();
                userObj.setName(name);
                userObj.setUserName(user);
                userObj.setEmail(email);

                byte[] salt = Encryption.generateSalt();
                byte[] hashedPassword = Encryption.generateHash(password, salt);

                boolean emailExists = userStorage.stream().anyMatch(u -> u.getEmail().equals(email));
                boolean userExists = userStorage.stream().anyMatch(u -> u.getUserName().equals(user));

                if (emailExists) {
                    sendNotification(new com.github.eventure.view.windowing.basic.Notification("Email já cadastrado!"));
                } else if (userExists) {
                    sendNotification(new com.github.eventure.view.windowing.basic.Notification("Nome de usuário já cadastrado!"));
                } else {
                    userObj.setPasswordHash(hashedPassword);
                    userObj.setPasswordSalt(salt);
                    userStorage.add(userObj); // Adiciona ao storage

                    sendNotification(new com.github.eventure.view.windowing.basic.Notification("Conta criada com sucesso!"));
                    System.out.println("Usuários criados: " + userStorage); // Teste: Exibe os usuários cadastrados
                }
            } else {sendNotification(new com.github.eventure.view.windowing.basic.Notification("Email não é válido!"));}
        });

        add(registerBtn, "span, center, wrap");

        var backBtn = new JButton("Voltar");
        backBtn.addActionListener(e -> {switchPage(getPageId(HomePage.class));});
        add(backBtn, "span, center, wrap");
}


private boolean isPasswordValid(short flags) {
    return (flags & PasswordRules.HAS_ENOUGH_CHARACTERS) != 0 &&
           (flags & PasswordRules.HAS_UPPERCASE_CHARACTERS) != 0 &&
           (flags & PasswordRules.HAS_LOWERCASE_CHARACTERS) != 0 &&
           (flags & PasswordRules.HAS_NUMBERS) != 0 &&
           (flags & PasswordRules.HAS_SYMBOLS) != 0 &&
           (flags & PasswordRules.HAS_INVALID_CHARACTERS) == 0;
} }

