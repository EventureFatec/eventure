package com.github.eventure.view.pages;

import javax.swing.*;

import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

import net.miginfocom.swing.*;

public class RegisterPage extends Page {

    public RegisterPage() {
        super(PageLayouts.REGULAR_LAYOUT);

        add(new JLabel ("Cadastro de Usuário"), "center, wrap");

        var nameField = new JTextField();
        var emailField = new JTextField();
        var passwordField = new JPasswordField();

        add(new JLabel("Nome Completo:"), "split 2");
        add(nameField, "growx, wrap");

        add(new JLabel("Senha:"), "split 2");
        add(passwordField, "growx, wrap");

        add(new JLabel("Email:"), "split 2");
        add(emailField, "growx, wrap");

        var registerBtn = new JButton("Cadastrar");
        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();

            if(name.isEmpty() || password.isEmpty() || email.isEmpty()) {
                sendNotification(new com.github.eventure.view.windowing.basic.Notification(("Todos os campos são obrigatórios!")));
            } else {
                sendNotification(new com.github.eventure.view.windowing.basic.Notification(("Usuário criado com sucesso!")));
            }
        });

        add(registerBtn, "span, center, wrap");

        var backBtn = new JButton("Voltar");
        backBtn.addActionListener(event -> switchPage(getPageId(HomePage.class)));
        add(backBtn, "span, center");
    }

}
