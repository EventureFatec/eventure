package com.github.eventure.view.windowing.basic;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.Timer;

import com.github.eventure.view.windowing.RoundedPanel;

import net.miginfocom.swing.MigLayout;

public class Notification extends RoundedPanel {
    private static final int CORNER_RADIUS = 16;
    private MigLayout panelLayout;
    private String internalMessage;

    public Notification(String message) {
        super(CORNER_RADIUS, false);
        internalMessage = message;
        setupLayout();
        setForeground(new Color(255, 0, 0, 0x60));
        var label = new JLabel(message);
        label.setFont(label.getFont().deriveFont(20f));
        add(label);

        new Timer(3000, e -> {
            Container parent = getParent();
            if (parent != null) {
                parent.remove(this);
                parent.revalidate();
                parent.repaint();
            }
        }).start();
    }

    private void setupLayout() {
        panelLayout = new MigLayout();
        setLayout(panelLayout);
    }

    public String getInternalMessage() {
        return internalMessage;
    }
}
