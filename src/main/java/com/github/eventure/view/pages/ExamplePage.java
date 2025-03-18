package com.github.eventure.view.pages;

import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

import javax.swing.JLabel;

public class ExamplePage extends Page {
    public ExamplePage() {
        super(PageLayouts.REGULAR_LAYOUT);
        add(new JLabel("Exemplo"), "wrap");
    }
}
