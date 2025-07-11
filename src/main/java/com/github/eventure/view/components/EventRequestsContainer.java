package com.github.eventure.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import com.github.eventure.controllers.EventController;
import com.github.eventure.controllers.IdController;
import com.github.eventure.controllers.UserController;
import com.github.eventure.model.Event;
import com.github.eventure.model.User;
import com.github.eventure.view.pages.MainPage;

public class EventRequestsContainer extends JPanel {
    public EventRequestsContainer(MainPage mainPage) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(600, 540));
        
        
        int userId = IdController.getIdUser();
        UserController userController = UserController.getInstance();
        EventController eventController = EventController.getInstance();

        User user = userController.findUserById(userId);
        if (user == null) return;

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(null);

        for (int eventId : user.getMyEventsList()) {
            Event event = eventController.findEventById(eventId);
            if (event == null) continue;

            for (int requesterId : event.getPendingRequestIds()) {
                User requester = userController.findUserById(requesterId);
                if (requester == null) continue;

                ImageIcon eventImage = new ImageIcon(event.getImagePath());
                ImageIcon userImage = new ImageIcon(requester.getProfilePic());

                EventRequestPanel requestPanel = new EventRequestPanel(
                        mainPage,
                		event.getTitle(),
                        event.getDate(),
                        requester.getName(),
                        requester.getEmail(),
                        requesterId,
                        eventId,
                        eventImage,
                        userImage
                );

                requestPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                contentPanel.add(Box.createVerticalStrut(10));
                contentPanel.add(requestPanel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new ModernScrollBarUI());

        add(scrollPane, BorderLayout.CENTER);
    }
}
