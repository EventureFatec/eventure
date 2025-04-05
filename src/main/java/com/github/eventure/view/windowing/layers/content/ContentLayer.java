package com.github.eventure.view.windowing.layers.content;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.github.eventure.view.windowing.RootPanel;
import com.github.eventure.view.windowing.basic.Notification;

public class ContentLayer extends JPanel {
    private RootPanel parentRootPanel;
    private BorderLayout panelLayout;

    public ContentLayer(RootPanel parent) {
        parentRootPanel = parent;
        setupLayout();
        setupContent();
    }

    private void setupLayout() {
        panelLayout = new BorderLayout();
        setLayout(panelLayout);
    }

    private void setupContent() {
        add(new ContentPanel(this), BorderLayout.CENTER);
    }

    public void sendNotification(Notification n) {
        parentRootPanel.sendNotification(n);
    }
}
