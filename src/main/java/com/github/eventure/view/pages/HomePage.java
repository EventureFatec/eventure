package com.github.eventure.view.pages;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

public class HomePage extends Page {
    private JButton tempButton;

    public HomePage() {
        super(PageLayouts.REGULAR_LAYOUT);
        add(new JLabel("Hello, World!"), "split 2, center");

        tempButton = new JButton("Next Page");
        tempButton.addActionListener((ActionEvent _) -> {
            switchPage(new SecondaryPage().getPageId());
        });
        add(tempButton, "wrap");

        add(new JLabel("This is on another line"));
    }
}
