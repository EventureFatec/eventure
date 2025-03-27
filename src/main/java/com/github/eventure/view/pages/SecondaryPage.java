package com.github.eventure.view.pages;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

public class SecondaryPage extends Page {
    private JButton tempButton;

    public SecondaryPage() {
        super(PageLayouts.REGULAR_LAYOUT);
        // setDefault(true);
        add(new JLabel("Secondary Page!"), "center, wrap");

        tempButton = new JButton("Previous Page");
        tempButton.addActionListener(event -> {
            switchPage(getPageId(HomePage.class));
        });
        add(tempButton);
    }
}
