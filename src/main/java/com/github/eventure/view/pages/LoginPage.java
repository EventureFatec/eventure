package com.github.eventure.view.pages;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.eventure.controllers.PasswordController;
import com.github.eventure.model.Account;
import com.github.eventure.model.Session;
import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

public class LoginPage extends Page {
    private static final String EXISTING_USERNAME = "claudinhobucetudo";
    private JTextField inputField;
    private JLabel label;

    public LoginPage() {
        super(PageLayouts.REGULAR_LAYOUT);
        setDefault(true);


        inputField = new JTextField();
        add(inputField, "center, grow, span");

        JButton loginButton = new JButton("Login");
        loginButton.setFont(loginButton.getFont().deriveFont(48f));
        loginButton.addActionListener(event -> tryLogin());
        add(loginButton, "center, grow, span");

        label = new JLabel("");
        label.setFont(label.getFont().deriveFont(32f));
        add(label, "span");
    }

    public void tryLogin() {
        // Verify that user exists
        var account = new Account();
        var user = inputField.getText();
        if (user.equals(EXISTING_USERNAME) && Session.login(account)) {
            clearFields();
            switchPage(getPageId(HomePage.class));
        } else {
            label.setText("LOGOU NÃO, ARROMBADO");
        }
    }

    public void clearFields() {
        label.setText("");
    }
}
