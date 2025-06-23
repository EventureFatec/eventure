package com.github.eventure.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EventRequestPanel extends JPanel {
    public EventRequestPanel(String eventName, String eventDate, String userName, String userEmail,
                             ImageIcon eventImage, ImageIcon userImage) {
        setLayout(null);
        setPreferredSize(new Dimension(450, 180));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        
        JLabel eventImageLabel = new JLabel();
        eventImageLabel.setIcon(new ImageIcon(eventImage.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
        eventImageLabel.setBounds(20, 20, 60, 60);
        add(eventImageLabel);

        
        JLabel title = new JLabel("Event Requests");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setBounds(100, 20, 200, 20);
        add(title);

        
        JLabel subtitle = new JLabel(eventName + " - " + eventDate);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setBounds(100, 45, 250, 15);
        add(subtitle);

        
        JTextField requestField = new JTextField("✓ Request");
        requestField.setEditable(false);
        requestField.setBounds(100, 70, 200, 25);
        add(requestField);
        JLabel userImageLabel = new JLabel();
        userImageLabel.setIcon(new ImageIcon(userImage.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
        userImageLabel.setBounds(100, 100, 40, 40);
        add(userImageLabel);
        JLabel userInfo = new JLabel(userName + " - " + userEmail);
        userInfo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        userInfo.setBounds(150, 110, 250, 20); 
        add(userInfo);

        JButton acceptButton = new JButton("Accept Request");
        acceptButton.setBounds(100, 150, 130, 25);
        acceptButton.setBackground(new Color(0x007BFF));
        acceptButton.setForeground(Color.WHITE);
        acceptButton.setFocusPainted(false);
        add(acceptButton);

        JButton declineButton = new JButton("Decline Request");
        declineButton.setBounds(240, 150, 130, 25);
        declineButton.setBackground(new Color(0xEEEEEE));
        declineButton.setForeground(Color.BLACK);
        declineButton.setFocusPainted(false);
        add(declineButton);
    }
}
