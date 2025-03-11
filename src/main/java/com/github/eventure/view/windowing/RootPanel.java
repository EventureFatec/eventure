package com.github.eventure.view.windowing;

import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;

import com.github.eventure.view.windowing.layers.ContentLayer;

public class RootPanel extends JLayeredPane {
    private OverlayLayout layout;

    public RootPanel() {
        super();

        setupLayout();
        setupAlignment();
        setupLayers();
    }

    private void setupLayout() {
        layout = new OverlayLayout(this);
        setLayout(layout);
    }

    private void setupAlignment() {
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }

    private void setupLayers() {
        add(new ContentLayer(), DEFAULT_LAYER);
    }
}
