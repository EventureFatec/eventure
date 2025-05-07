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

import com.github.eventure.view.MainFrame;

public class WelcomePage extends JPanel {
	private MainFrame mainFrame;
    public WelcomePage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(1280, 720));
        this.setBackground(new Color(0x330065));
        this.setLayout(null);

//        this.addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                System.out.println("Mouse position: " + e.getX() + ", " + e.getY());
//            }
//        });

        var bg = new JLabel();
//        var bgIcon = new ImageIcon("src/main/java/com/github/eventure/view/pages/BOASVINDAS.png");
        var bgIcon = new ImageIcon(getClass().getResource("/BOASVINDAS.png"));
        bg.setIcon(bgIcon);

        bg.setBounds(50, 10, bgIcon.getIconWidth(), bgIcon.getIconHeight());

        this.add(bg);

        initComponents();
    } 

    private void initComponents() {
        
        var loginBtn = new JButton();
        loginBtn.setBounds(258, 406, 341, 53);        
        loginBtn.setOpaque(false);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setBorderPainted(false);
        add(loginBtn);

        var registerBtn = new JButton();
        registerBtn.setBounds(258, 491, 341, 53);        
        registerBtn.setOpaque(false);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setBorderPainted(false);
        add(registerBtn);
        // troquei a ordem dos botoes pois estavam ao contrario
        loginBtn.addActionListener(e -> {
            mainFrame.showPanel("login");
        });

        registerBtn.addActionListener(e -> {

        	mainFrame.showPanel("register");
        });

    }
}
