package com.github.eventure.view.pages;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
        wrapperPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        this.add(wrapperPanel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(new Color(180, 180, 180, 100)); // sombra
                g2.setStroke(new BasicStroke(3));
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 25, 25);
                g2.dispose();
            }
        };
        mainPanel.setOpaque(false);
        mainPanel.setPreferredSize(new Dimension(420, 600));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int y = 0;

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/EVENTURE-LOGO.png"));
        Image logoImageScaled = logoIcon.getImage().getScaledInstance(320, 90, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(logoImageScaled);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBorder(new EmptyBorder(0, 0, 30, 0));

        gbc.gridy = y++;
        gbc.weighty = 0;
        mainPanel.add(logoLabel, gbc);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 20);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 18);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(labelFont);
        loginLabel.setForeground(new Color(0x330065));
        gbc.gridy = y++;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(loginLabel, gbc);

        loginField = new JTextField();
        loginField.setFont(fieldFont);
        loginField.setPreferredSize(new Dimension(350, 40));
        loginField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0x330065), 2, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        loginField.setCaretColor(new Color(0x330065));
        loginField.setToolTipText("Digite seu login");
        gbc.gridy = y++;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginField, gbc);

        JLabel passwordLabel = new JLabel("Senha");
        passwordLabel.setFont(labelFont);
        passwordLabel.setForeground(new Color(0x330065));
        gbc.gridy = y++;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(fieldFont);
        passwordField.setPreferredSize(new Dimension(350, 40));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0x330065), 2, true),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        passwordField.setCaretColor(new Color(0x330065));
        passwordField.setToolTipText("Digite sua senha");
        gbc.gridy = y++;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(passwordField, gbc);

        loginButton = createGradientButton("Entrar");
        gbc.gridy = y++;
        gbc.weighty = 0;
        gbc.insets = new Insets(25, 20, 10, 20);
        mainPanel.add(loginButton, gbc);

        registerBtn = new JButton("Cadastre-se");
        registerBtn.setFont(labelFont);
        registerBtn.setForeground(new Color(0x330065));
        registerBtn.setContentAreaFilled(false);
        registerBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        gbc.gridy = y++;
        gbc.weighty = 0;
        mainPanel.add(registerBtn, gbc);

        gbc.gridy = y++;
        gbc.weighty = 1.0;
        mainPanel.add(Box.createVerticalGlue(), gbc);

        wrapperPanel.add(mainPanel);

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

    private JButton createGradientButton(String text) {
        JButton button = new JButton(text) {
            private boolean hover = false;

            {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                setFocusPainted(false);
                setContentAreaFilled(false);
                setForeground(Color.WHITE);
                setFont(new Font("Segoe UI", Font.BOLD, 20));
                setPreferredSize(new Dimension(350, 45));

                addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        hover = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        hover = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color colorStart = new Color(0x330065);
                Color colorEnd = new Color(0x550099);

                if (hover) {
                    colorStart = colorStart.brighter();
                    colorEnd = colorEnd.brighter();
                }

                GradientPaint gp = new GradientPaint(0, 0, colorStart, 0, getHeight(), colorEnd);
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                var fm = g2.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int textHeight = fm.getAscent();

                g2.setColor(getForeground());
                g2.drawString(getText(), (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 2);

                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(0x220050));
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 30, 30);
                g2.dispose();
            }
        };

        return button;
    }
}
