package com.github.eventure.view.pages;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import com.github.eventure.view.windowing.basic.Page;
import com.github.eventure.view.windowing.basic.PageLayouts;
import com.github.eventure.view.windowing.basic.Notification;

public class HomePage extends Page {
    public HomePage() {
        super(PageLayouts.GALLERY_LAYOUT);
        setDefault(true);

        add(new JLabel("GALERIA"), "span");
        add(new JLabel("Example 1"), "wrap");

        var p = new JPanel(new MigLayout("fillx", PageLayouts.REGULAR_LAYOUT));
        var b = new JButton("Send Notification");
        b.addActionListener(event -> {
            sendNotification(new Notification("example notification"));
        });
        p.add(new JButton("Button 1"), "split 1");
        var b2 = new JButton("Button 2");
        b2.addActionListener(event -> switchPage(getPageId(SecondaryPage.class)));
        p.add(b2, "wrap");
        p.add(b, "growx, span");

        add(p, "");
        add(new JLabel("Example 3"), "span");
    }
}
