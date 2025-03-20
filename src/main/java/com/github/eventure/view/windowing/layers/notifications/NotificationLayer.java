package com.github.eventure.view.windowing.layers.notifications;

import java.util.ArrayList;

import javax.swing.JPanel;

import com.github.eventure.view.windowing.RootPanel;
import com.github.eventure.view.windowing.basic.Notification;

public class NotificationLayer extends JPanel {
    private RootPanel parentRootPanel;
    private ArrayList<Notification> notificationHistory;

    public NotificationLayer(RootPanel parent) {
        parentRootPanel = parent;
        notificationHistory = new ArrayList<>();
        setOpaque(false);
    }

    public void sendNotification(Notification n) {
        notificationHistory.addLast(n);
        showLatestNotification();
    }

    private void showLatestNotification() {
        var notification = notificationHistory.getLast();
        System.out.println(notification.getInternalMessage());
    }
}
