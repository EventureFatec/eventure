package com.github.eventure.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.User;
import com.github.eventure.view.pages.EventPanelForEditPanel;
import com.github.eventure.view.pages.MainPage;

public class EventPanelConsult extends JPanel {
    private int idEvento;
    public EventPanelConsult(String title, String location, String date, String imagePath,int id,MainPage mainPage) {
        
        idEvento = id;
        
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
//        infoPanel.setBackground(new Color(0xe5d8fd));
        infoPanel.setBackground(new Color(0xFFFFFF));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel titleLabel = new JLabel("<html><b>" + title + "</b></html>");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0x4A148C));
        
        JLabel locationLabel = new JLabel(location);
        locationLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        locationLabel.setForeground(new Color(0x777777));

        JLabel dateTimeLabel = new JLabel(date);
        dateTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        dateTimeLabel.setForeground(new Color(0x777777)); 

        infoPanel.add(titleLabel);
        infoPanel.add(locationLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(dateTimeLabel);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Clicou no evento com ID: " + idEvento);
                var displayEventPanel = new DisplayEvent(idEvento);
                // chamar a tela exibir evento completo
                mainPage.showMainPanel(displayEventPanel,1);
            }
        });
        add(infoPanel, BorderLayout.CENTER);
    }
 public void initComponets()
 {
	 
 }
}

