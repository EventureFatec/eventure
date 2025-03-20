package com.github.eventure.view.windowing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.intellijthemes.FlatLightFlatIJTheme;
import com.github.eventure.Constants;


public class Window extends JFrame {
    private final Dimension MINIMUM_SIZE = new Dimension(800, 600);
    private RootPanel rootPanel;

    public Window() {
        setupLookAndFeel();
        setupLayout();
        setupRootPanel();
        setupBehavior();
    }

    private void setupBehavior () {
        // Size and Position
        setMinimumSize(MINIMUM_SIZE);
        setSize(MINIMUM_SIZE);
        setLocationRelativeTo(null);
        setResizable(true);

        // Close Behavior
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Title and Icon
        setTitle(Constants.PROJECT_NAME);

        // Visibility
        setVisible(true);
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
    }

    private void setupRootPanel() {
        rootPanel = new RootPanel();
        add(rootPanel, BorderLayout.CENTER);
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
