package com.github.eventure.view.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
    // private User user;

    public LoginPage(MainFrame frame) {
        this.frame = frame;
        this.setLayout(new BorderLayout());
        // this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(0x330065));

        initDesign();
        initComponents();
    }

    private void initComponents() {

        loginField = new JTextField();
        // loginField.setBounds(247, 227, 240, 36);

        passwordField = new JPasswordField();
        // passwordField.setBounds(247, 331, 240, 36);

        loginButton = new JButton();
        loginButton.setBounds(203, 420, 314, 48);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        registerBtn = new JButton();
        registerBtn.setBounds(245, 543, 229, 45);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setBorderPainted(false);
        registerBtn.setFocusPainted(false);
        registerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerBtn.addActionListener(e -> {
            loginField.setText(" ");
            passwordField.setText(" ");
            frame.showPanel("register");
        });

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
                boolean loginSuccessful = userController.login(username, password);

                if (loginSuccessful) {
                    // Recupera o objeto User completo do controlador
                    User loggedUser = userController.findUserByEmailOrUsername(username);
                    if (loggedUser != null) {
                        Session.login(loggedUser); // Salva o usuário logado na sessão
                        JOptionPane.showMessageDialog(null, "Login Realizado com sucesso.");
                        loginField.setText("");
                        passwordField.setText("");
                        frame.showPanel("home");
                    } else {
                        JOptionPane.showMessageDialog(null, "Login falhou. Tente novamente.");
                    }
                }
            };

        });
    }

    private void initDesign() {
        var mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS)); // Conteúdos serão organizados verticalmente
        mainPanel.setBackground(new Color(0xcfcfcf)); // Painel com a cor cinza

        // Carrega a imagem
        var logoIcon = new ImageIcon(getClass().getResource("/EVENTURE-LOGO.png"));

        // Redimensiona a imagem
        var logoImageScaled = logoIcon.getImage().getScaledInstance(360, 98, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(logoImageScaled);

        // JLabel para exibir a imagem redimensionada
        var logoLabel = new JLabel(logoIcon);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza no BoxLayout

        // Adiciona o logoLabel
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço acima
        mainPanel.add(logoLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espaço abaixo
    }
}
