package com.github.eventure.view.windowing;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;


public class Window extends JFrame {
    private RootPanel panel;

    public Window() {
        setupLookAndFeel();
        setupRootPanel();
        setVisible(true);
    }

    private void setupRootPanel() {
        panel = new RootPanel();
        add(panel);
    }

    private void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatLightFlatIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println(e.getStackTrace());
        }
    }

    public static void run() {
        SwingUtilities.invokeLater(() -> new Window());
    }
}
