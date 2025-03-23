package com.github.eventure.view.windowing.basic;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.eventure.view.windowing.RoundedPanel;

import net.miginfocom.swing.MigLayout;

public class Notification extends RoundedPanel {
    private static final int CORNER_RADIUS = 16;
    private MigLayout panelLayout;
    private String internalMessage;

    public Notification(String message) {
        super(CORNER_RADIUS);
        internalMessage = message;
        setupLayout();
        setForeground(new Color(255, 0, 0, 0xA0));
        var label = new JLabel(message);
        label.setFont(label.getFont().deriveFont(48f));
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
