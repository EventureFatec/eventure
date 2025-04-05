package com.github.eventure.view.windowing.layers.notifications;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.eventure.view.windowing.RootPanel;
import com.github.eventure.view.windowing.basic.Notification;

import net.miginfocom.swing.MigLayout;

public class NotificationLayer extends JPanel {
    // TODO: Use a BorderLayout with an internal NotificationPanel instead
    private RootPanel parentRootPanel;
    private NotificationPanel notificationPanel;
    private BorderLayout panelLayout;
    private ArrayList<Notification> notificationHistory;

    public NotificationLayer(RootPanel parent) {
        parentRootPanel = parent;
        notificationHistory = new ArrayList<>();
        setOpaque(false);
        setupLayout();
        setupNotifications();
    }

    private void setupLayout() {
        panelLayout = new BorderLayout();
        setLayout(panelLayout);
    }

    private void setupNotifications() {
        add((notificationPanel = new NotificationPanel()), BorderLayout.CENTER);
    }

    public void sendNotification(Notification n) {
        notificationHistory.addLast(n);
        notificationPanel.showNotification(n);
    }
}
