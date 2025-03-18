package com.github.eventure.view.windowing.layers.content;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ContentLayer extends JPanel {
    private BorderLayout panelLayout;

    public ContentLayer() {
        setupLayout();
        setupContent();
    }

    private void setupLayout() {
        panelLayout = new BorderLayout();
        setLayout(panelLayout);
    }

    private void setupContent() {
        add(new ContentPanel(), BorderLayout.CENTER);
    }
}
