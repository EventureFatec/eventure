package com.github.eventure.view.windowing.basic;

import javax.swing.JPanel;

public class Notification extends JPanel {
    private String internalMessage;

    public Notification(String message) {
        internalMessage = message;
    }

    public String getInternalMessage() {
        return internalMessage;
    }
}
