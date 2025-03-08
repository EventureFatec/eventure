package com.github.eventure.view.windowing;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import com.github.eventure.view.windowing.layers.ContentLayer;

public class LayeredRootPanel extends JPanel {
    private OverlayLayout rootLayout;
    private JLayeredPane rootPanel;

    public LayeredRootPanel() {
        super();
        setBackground(Color.RED);
        setupLayout();
        setupLayers();
        setupContent();
    }

    private void setupLayout() {
        setLayout(new BorderLayout());
    }

    private void setupLayers() {
        rootPanel = new JLayeredPane();
        rootLayout = new OverlayLayout(rootPanel);
        rootPanel.setLayout(rootLayout);
        rootPanel.setAlignmentX(JLayeredPane.CENTER_ALIGNMENT);
        rootPanel.setAlignmentY(JLayeredPane.CENTER_ALIGNMENT);
        add(rootPanel, BorderLayout.CENTER);
    }

    /**
     * Lives on the {@link JLayeredPane}'s DEFAULT_LAYER
     */
    private void setupContent() {
        rootPanel.add(new ContentLayer(), JLayeredPane.DEFAULT_LAYER);
    }
}
