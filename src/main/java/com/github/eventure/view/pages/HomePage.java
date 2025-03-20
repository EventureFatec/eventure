package com.github.eventure.view.pages;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

public class HomePage extends Page {
    private JButton tempButton;

    public HomePage() {
        super(PageLayouts.GALLERY_LAYOUT);

        add(new JLabel("GALERIA"), "span");
        add(new JLabel("Example 1"), "");

        var p = new JPanel(new MigLayout("fillx", PageLayouts.REGULAR_LAYOUT));
        p.add(new JButton("Button 1"), "wrap");
        p.add(new JButton("Button 2"), "");

        add(p, "wrap");
        add(new JLabel("Example 3"), "");
    }
}
