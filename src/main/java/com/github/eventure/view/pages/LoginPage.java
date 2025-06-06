package com.github.eventure.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Session;
import com.github.eventure.model.User;
import com.github.eventure.view.MainFrame;

public class LoginPage extends JPanel {
    private MainFrame frame;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerBtn;
    private UserController userController = UserController.getInstance();

    public LoginPage(MainFrame frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x330065));
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x330065));

        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(new Color(0x330065));
        this.add(wrapperPanel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(0xcfcfcf));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 20, 10, 20); // Espaçamento entre componentes
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        int y = 0;

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/EVENTURE-LOGO.png"));
        Image logoImageScaled = logoIcon.getImage().getScaledInstance(360, 98, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(logoImageScaled);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        gbc.gridy = y++;
        gbc.weighty = 0.2;
        mainPanel.add(logoLabel, gbc);

        // Campo Login
        JLabel loginLabel = new JLabel("Login:");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.gridy = y++;
        gbc.weighty = 0.05;
        mainPanel.add(loginLabel, gbc);

        loginField = new JTextField();
        gbc.gridy = y++;
        gbc.weighty = 0.1;
        mainPanel.add(loginField, gbc);

        // Campo Senha
        passwordField = new JPasswordField();
        gbc.gridy = y++;
        gbc.weighty = 0.1;
        mainPanel.add(passwordField, gbc);

        // Botão Entrar
        loginButton = new JButton("Entrar");
        gbc.gridy = y++;
        gbc.weighty = 0.1;
        mainPanel.add(loginButton, gbc);

        // Botão Registrar
        registerBtn = new JButton("Registrar-se");
        gbc.gridy = y++;
        gbc.weighty = 0.1;
        mainPanel.add(registerBtn, gbc);

        // Espaço inferior
        gbc.gridy = y++;
        gbc.weighty = 1.0; // Ocupa o espaço restante
        mainPanel.add(Box.createVerticalGlue(), gbc);

        // Centraliza o mainPanel
        wrapperPanel.add(mainPanel);

        // Ações
        loginButton.addActionListener((ActionEvent e) -> {
            String username = loginField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
                return;
            }

            boolean loginSuccessful = userController.login(username, password);

            if (loginSuccessful) {
                User loggedUser = userController.findUserByEmailOrUsername(username);
                if (loggedUser != null) {
                    Session.login(loggedUser);
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso.");
                    loginField.setText("");
                    passwordField.setText("");
                    frame.showPanel("home");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Credenciais inválidas.");
            }
        });

        registerBtn.addActionListener(e -> {
            loginField.setText("");
            passwordField.setText("");
            frame.showPanel("register");
        });
    }

}
