package com.github.eventure.view.windowing.layers.notifications;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.github.eventure.view.windowing.basic.Notification;

public class NotificationPanel extends JPanel {
    private MigLayout panelLayout;

    public NotificationPanel() {
        setupLayout();
        setOpaque(false);
    }

    private void setupLayout() {
        panelLayout = new MigLayout("ttb, fillx, debug", "50%[]3%", "5%[]5%");
        setLayout(panelLayout);
    }

    public void showNotification(Notification n) {
        add(n, "grow, span");
        System.out.println(n.getInternalMessage());
        revalidate();
    }
}
