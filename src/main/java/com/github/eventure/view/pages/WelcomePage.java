package com.github.eventure.view.pages;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.github.eventure.view.MainFrame;

public class WelcomePage extends JPanel {
    private MainFrame mainFrame;

    private JButton loginBtn;
    private JButton registerBtn;

    public WelcomePage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setBackground(new Color(0x330065));
        this.setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0x330065));

        var wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.setBackground(new Color(0x330065));
        wrapperPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        this.add(wrapperPanel, BorderLayout.CENTER);

        var mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(new Color(180, 180, 180, 100)); 
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

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 20);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 52);

        var welcomeLabel = new JLabel("Bem Vindo!");
        welcomeLabel.setFont(titleFont);
        welcomeLabel.setForeground(new Color(0x330065));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = y++;
        gbc.weighty = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(welcomeLabel, gbc);

        registerBtn = createGradientButton("Cadastre-se!");
        gbc.gridy = y++;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registerBtn, gbc);

        loginBtn = new JButton("Já possui conta? Entre!");
        loginBtn.setFont(labelFont);
        loginBtn.setForeground(new Color(0x330065));
        loginBtn.setContentAreaFilled(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        gbc.gridy = y++;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginBtn, gbc);

        wrapperPanel.add(mainPanel);

        registerBtn.addActionListener(e -> {
            mainFrame.showPanel("register");
        });

        loginBtn.addActionListener(e -> {
            mainFrame.showPanel("login");
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
