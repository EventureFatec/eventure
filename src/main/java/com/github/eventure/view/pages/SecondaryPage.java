package com.github.eventure.view.pages;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.github.eventure.view.windowing.Page;
import com.github.eventure.view.windowing.PageLayouts;
import com.github.eventure.view.windowing.RootPanel;

public class SecondaryPage extends Page {
    private JButton tempButton;

    public SecondaryPage() {
        super(PageLayouts.REGULAR_LAYOUT);
        add(new JLabel("Secondary Page!"), "center, wrap");

        tempButton = new JButton("Previous Page");
        tempButton.addActionListener((ActionEvent _) -> {
            switchPage(HomePage.class.getSimpleName());
        });
        add(tempButton);
    }
}
