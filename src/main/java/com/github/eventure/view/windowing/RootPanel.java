package com.github.eventure.view.windowing;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import com.github.eventure.view.windowing.layers.content.ContentLayer;

public class RootPanel extends JLayeredPane {
    private OverlayLayout panelLayout;

    public RootPanel() {
        setupLayout();
        setupLayers();
    }

    private void setupLayout() {
        // Layout
        panelLayout = new OverlayLayout(this);
        setLayout(panelLayout);

        // Alignment
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }

    private void setupLayers() {
        add(new ContentLayer(), DEFAULT_LAYER);
    }
}
