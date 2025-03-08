package com.github.eventure.view.windowing.basic;

import javax.swing.JPanel;

import com.github.eventure.view.windowing.layers.ContentLayer;

import net.miginfocom.swing.MigLayout;

public class Page extends JPanel {
    private static final String DEFAULT_CONSTRAINT = "fill";
    private MigLayout layout;
    private ContentLayer parentRootPanel;

    public Page(String layoutString, ContentLayer rootPanel) {
        layout = new MigLayout(DEFAULT_CONSTRAINT, layoutString);
        parentRootPanel = rootPanel;
    }

    public Page(String layoutString) {
        layout = new MigLayout(DEFAULT_CONSTRAINT, layoutString);
    }

    public String getPageId() {
        return this.getClass().getSimpleName();
    }

    public void switchPage(String id) {
        if (parentRootPanel == null) {
            parentRootPanel = (ContentLayer) getParent();
            System.out.println(parentRootPanel);
        }
        parentRootPanel.switchPage(id);
    }

    public void sendNotification() {}  // TODO: Implement this
}
