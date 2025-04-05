package com.github.eventure.view.windowing;

import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import com.github.eventure.view.windowing.basic.Notification;
import com.github.eventure.view.windowing.layers.content.ContentLayer;
import com.github.eventure.view.windowing.layers.notifications.NotificationLayer;

public class RootPanel extends JLayeredPane {
    private OverlayLayout panelLayout;
    private ContentLayer content;
    private NotificationLayer notifications;

    public RootPanel() {
        setupLayout();
        setupLayers();
    }

    private void setupLayout() {
        // Layout
        panelLayout = new OverlayLayout(this);
        setLayout(panelLayout);

        // Alignment
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }

    private void setupLayers() {
        add((content = new ContentLayer(this)), DEFAULT_LAYER);
        add((notifications = new NotificationLayer(this)), POPUP_LAYER);
    }

    public void sendNotification(Notification n) {
        notifications.sendNotification(n);
    }
}
