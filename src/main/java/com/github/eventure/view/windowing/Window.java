package com.github.eventure.view.windowing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Window extends JFrame {
    private RootPanel panel;

    public Window() {
        add(panel);
        setVisible(true);
    }

    public static void run() {
        SwingUtilities.invokeLater(() -> new Window());
    }
}
