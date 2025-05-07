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

public class LoginPage extends JPanel {
    private MainFrame frame;
    private JTextField loginField;  // Corrigido nome da variável
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerBtn;
    private UserController userController = UserController.getInstance();

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
        
//        userController.createUser("allisson" , "thomas" , "Allisson7787@" , "allisson@gmail.com");
//        userController.print();
    }

    private void initComponents() {
        // Inicializa os campos e botões
        loginField = new JTextField();
        loginField.setBounds(247, 227, 240, 36);

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
        registerBtn.addActionListener(e -> { frame.showPanel("register"); });

        this.add(loginField);
        this.add(passwordField);
        this.add(loginButton);
        this.add(registerBtn);

        // Corrigido ActionListener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginField.getText();
                String password = new String(passwordField.getPassword());
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
                    return;
                }

                userController = UserController.getInstance();
//                userController.createUser("allisson" , "thomas" , "Allisson7787@" , "allisson@gmail.com");
                boolean loginSuccessful = userController.login(username, password);
                if (loginSuccessful) {
                    frame.showPanel("welcome");
                } else {
                    JOptionPane.showMessageDialog(null, "Login falhou. Tente novamente.");
                }
            }
        });
    }
}
