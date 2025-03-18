package com.github.eventure.view.windowing;

import javax.swing.JLayeredPane;
<<<<<<< Updated upstream
import javax.swing.OverlayLayout;

import com.github.eventure.view.windowing.layers.ContentLayer;

public class RootPanel extends JLayeredPane {
    private OverlayLayout layout;

    public RootPanel() {
        super();

        setupLayout();
        setupAlignment();
=======
import javax.swing.JPanel;
import javax.swing.OverlayLayout;

import com.github.eventure.view.windowing.layers.content.ContentLayer;

public class RootPanel extends JLayeredPane {
    private OverlayLayout panelLayout;

    public RootPanel() {
        setupLayout();
>>>>>>> Stashed changes
        setupLayers();
    }

    private void setupLayout() {
<<<<<<< Updated upstream
        layout = new OverlayLayout(this);
        setLayout(layout);
    }

    private void setupAlignment() {
=======
        // Layout
        panelLayout = new OverlayLayout(this);
        setLayout(panelLayout);

        // Alignment
>>>>>>> Stashed changes
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);
    }

    private void setupLayers() {
        add(new ContentLayer(), DEFAULT_LAYER);
    }
}
