package com.github.eventure.view.windowing;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class Page extends JPanel {
    private static final String DEFAULT_CONSTRAINT = "fill";
    private MigLayout layout;
    private RootPanel parentRootPanel;

    public Page(String layoutString, RootPanel rootPanel) {
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
            parentRootPanel = (RootPanel) getParent();
            System.out.println(parentRootPanel);
        }
        parentRootPanel.switchPage(id);
    }

    public void sendNotification() {}  // TODO: Implement this
}
