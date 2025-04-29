package com.github.eventure.view.pages;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class WelcomePage extends JPanel {
    public WelcomePage() {

        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(0x330065));
        this.setLayout(null);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("Mouse position: " + e.getX() + ", " + e.getY());
            }
        });

        var bg = new JLabel();
        var bgIcon = new ImageIcon("src/main/java/com/github/eventure/view/pages/BOASVINDAS.png");
        bg.setIcon(bgIcon);

        bg.setBounds(50, 10, bgIcon.getIconWidth(), bgIcon.getIconHeight());

        this.add(bg);

        initComponents();
    } 

    private void initComponents() {
        
        var registerBtn = new JButton();
        registerBtn.setBounds(258, 406, 341, 53);        
        registerBtn.setOpaque(false);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setBorderPainted(false);
        add(registerBtn);

        var loginBtn = new JButton();
        loginBtn.setBounds(258, 491, 341, 53);        
        loginBtn.setOpaque(false);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setBorderPainted(false);
        add(loginBtn);

        registerBtn.addActionListener(e -> {
            var registerPage = new RegisterPage();
            registerPage.setVisible(true);
            this.setVisible(false);
        });

        loginBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Página de login ainda não implementado!");
        });

    }
}
