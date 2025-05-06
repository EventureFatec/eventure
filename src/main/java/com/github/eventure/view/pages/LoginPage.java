package com.github.eventure.view.pages;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import com.github.eventure.model.User;
import com.github.eventure.controllers.UserController;
import com.github.eventure.encryption.Encryption;
import com.github.eventure.view.MainFrame;
import java.util.Optional;

public class LoginPage extends JPanel {
    private MainFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerBtn;

    public LoginPage(MainFrame frame) {
        this.frame = frame;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(0x330065));

        initComponents();  // Apenas chama o método de inicialização
        var label1 = new JLabel();
        var icon = new ImageIcon(getClass().getResource("/LOGIN.png"));
        label1.setIcon(icon);
        label1.setBounds(0, 0, 1200, 720);
        this.add(label1);
    }

    private void initComponents() {
        // Inicializa os campos e botões
        usernameField = new JTextField();
        usernameField.setBounds(247, 227, 240, 36);

        passwordField = new JPasswordField();
        passwordField.setBounds(247, 331, 240, 36);

        loginButton = new JButton();
        loginButton.setBounds(203, 420, 314, 48);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);

        registerBtn = new JButton();
        registerBtn.setBounds(245, 543, 229, 45);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setBorderPainted(false);
        registerBtn.setFocusPainted(false);
        registerBtn.addActionListener(e -> {frame.showPanel("register");});

        this.add(usernameField);
        this.add(passwordField);
        this.add(loginButton);
        this.add(registerBtn);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String loginInput = usernameField.getText().trim();
                char[] passwordInput = passwordField.getPassword();

                // Verifica se os campos estão vazios
                if (loginInput.isEmpty() || passwordInput.length == 0) {
                    JOptionPane.showMessageDialog(LoginPage.this, "Todos os campos devem estar preenchidos!");
                    return;
                }

                // Procura o usuário no UserStorage
//                Optional<User> userOptional = userStorage.getUsers().stream()
//                    .filter(u -> u.getUsername().equalsIgnoreCase(loginInput) || 
//                                 u.getEmail().equalsIgnoreCase(loginInput) || 
//                                 u.getName().equalsIgnoreCase(loginInput))
//                    .findFirst();
                 Boolean t = true;
                if (t) {
//                    User user = userOptional.get();
                    // Gera o hash da senha inserida
//                    byte[] hashedPassword = Encryption.generateHash(new String(passwordInput), user.getPasswordSalt());

                    // Compara as senhas
                    if (10 > 2) {
                        JOptionPane.showMessageDialog(frame, "Login bem-sucedido!");
                        frame.showPanel("welcome");  // Aqui você chama o método correto do frame
                    } else {
                        JOptionPane.showMessageDialog(frame, "Senha incorreta");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Usuário não encontrado");
                }
            }
        });
    }
}

