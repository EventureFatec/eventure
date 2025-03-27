package com.github.eventure.view.pages;

import javax.swing.JLabel;

import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;

public class ExamplePage extends Page {
    public ExamplePage() {
        super(PageLayouts.REGULAR_LAYOUT);
        add(new JLabel("PUTA QUE PARIU!"));
    }
}
