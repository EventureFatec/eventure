package com.github.eventure.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Event;
import com.github.eventure.model.User;
import com.github.eventure.view.pages.EventPanel;
import com.github.eventure.view.pages.EventPanelForEditPanel;
import com.github.eventure.view.pages.MainPage;

public class EventPanelEdit extends JPanel {

    public EventPanelEdit(MainPage mainPage) {
        setLayout(null);
        setPreferredSize(new Dimension(1130, 590));
        setBackground(Color.decode("#F5F5F5"));
        JPanel header = new JPanel(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 1130, 60);

        JLabel logo = new JLabel("Eventure");
        logo.setFont(new Font("SansSerif", Font.BOLD, 20));
        logo.setBounds(20, 15, 200, 30);

        JButton menuButton = new JButton("⋮");
        menuButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setBorderPainted(false);
        menuButton.setBounds(1000, 15, 40, 30);

        header.add(logo);
        header.add(menuButton);
        add(header);

        JPanel contentPanel = new JPanel(null);
        contentPanel.setBackground(Color.decode("#F5F5F5"));

        JLabel label = new JLabel("Escolha um Evento Para Editar", SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 22));
        label.setBounds(0, 10, 1130, 40);
        contentPanel.add(label);

        var userController = UserController.getInstance();
        var eventController = EventController.getInstance();
        User user = userController.findUserById(IdController.getIdUser());

        int cardWidth = 300;
        int cardHeight = 240;
        int spaceX = 20;
        int spaceY = 20;
        int cardsPerRow = 3;

        for (int i = 0; i < user.getMyEventsList().size(); i++) {
            int idEvent = user.getMyEventsList().get(i);
            var event = eventController.findEventById(idEvent);

            EventPanelForEditPanel panel = new EventPanelForEditPanel(
                event.getTitle(),
                event.getAddress().getEstado() + ", " + event.getAddress().getCidade(),
                event.getDate(),
                event.getImagePath(),
                event.getId(),
                mainPage
            );

            int row = i / cardsPerRow;
            int col = i % cardsPerRow;
            int x = 50 + spaceX + (cardWidth + spaceX) * col;
            int y = 60 + (cardHeight + spaceY) * row;

            panel.setBounds(x, y, cardWidth, cardHeight);
            contentPanel.add(panel);
        }
      
        int rows = (int) Math.ceil(user.getMyEventsList().size() / (double) cardsPerRow);
        int contentHeight = 60 + rows * (cardHeight + spaceY);
        contentPanel.setPreferredSize(new Dimension(1130, contentHeight));

        
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBounds(0, 60, 1130, 530); 
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        add(scrollPane);
    }
}
