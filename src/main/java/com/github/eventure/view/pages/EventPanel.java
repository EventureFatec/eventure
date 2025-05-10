package com.github.eventure.view.pages;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.eventure.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class EventPanel extends JPanel {
    private MainFrame mainFrame;

    public EventPanel(String title, String location, String date, String imagePath) {
        

        // Tamanho fixo do painel
        Dimension panelSize = new Dimension(300, 240);
        setPreferredSize(panelSize);
        setMaximumSize(panelSize);
        setMinimumSize(panelSize);

        this.setBackground(new Color(0xe5d8fd));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        // ===== Imagem =====
        
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaled = icon.getImage().getScaledInstance(300, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaled));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.NORTH);

        // ===== Informações =====
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(0xe5d8fd));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("<html><b>" + title + "</b></html>");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        JLabel locationLabel = new JLabel(location);
        locationLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        locationLabel.setForeground(Color.GRAY);

        JLabel dateTimeLabel = new JLabel(date);
        dateTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        dateTimeLabel.setForeground(new Color(255, 102, 0)); // laranja (#FF6600)

        infoPanel.add(titleLabel);
        infoPanel.add(locationLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(dateTimeLabel);

        add(infoPanel, BorderLayout.CENTER);
    }
 public void initComponets()
 {
	 
 }
}

