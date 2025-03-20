package com.github.eventure.view.windowing.basic;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class Notification extends JPanel {
    private MigLayout panelLayout;
    private String internalMessage;

    public Notification(String message) {
        internalMessage = message;
        setupLayout();
        setBackground(Color.RED);
        var label = new JLabel(message);
        add(label);
    }

    private void setupLayout() {
        panelLayout = new MigLayout();
        setLayout(panelLayout);
    }

    public String getInternalMessage() {
        return internalMessage;
    }
}
