package com.github.eventure.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.eventure.model.Community;

public class ModernCommunityCard extends JPanel {
    public ModernCommunityCard(Community community, boolean joined, ActionListener onClick) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 300));
        setMaximumSize(new Dimension(200, 300));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);
        setOpaque(true);

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(200, 140));
        imageLabel.setOpaque(true);

        if (community.getImagePath() != null && !community.getImagePath().isBlank()) {
            ImageIcon icon = new ImageIcon(community.getImagePath());
            Image scaled = icon.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaled));
        } else {
            imageLabel.setBackground(new Color(230, 230, 230));
        }

        JPanel infoPanel = new RoundedPanel(20);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel nameLabel = new JLabel("<html><b>" + community.getName() + "</b></html>");
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel descLabel = new JLabel("<html><div style='text-align: center; font-size: 11px; color: gray;'>"
                + (community.getDescription() == null ? "" : community.getDescription()) + "</div></html>");
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton actionButton = new JButton(joined ? "Sair" : "Entrar");
        actionButton.setBackground(Color.BLACK);
        actionButton.setForeground(Color.WHITE);
        actionButton.setFocusPainted(false);
        actionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        actionButton.setMaximumSize(new Dimension(100, 30));
        actionButton.addActionListener(onClick);

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(descLabel);
        infoPanel.add(Box.createVerticalStrut(15));
        infoPanel.add(actionButton);

        add(imageLabel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
    }

    static class RoundedPanel extends JPanel {
        private final int radius;
        public RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
