package com.github.eventure.view.windowing.basic;

import javax.swing.JPanel;

import com.github.eventure.view.windowing.layers.content.ContentPanel;

import net.miginfocom.swing.MigLayout;

public class Page extends JPanel {
    private static final String DEFAULT_CONSTRAINT = "fill";
    private static final String DEBUG_CONSTRAINT = DEFAULT_CONSTRAINT + ", debug";
    private boolean isDefault = false;
    private MigLayout layout;
    private ContentPanel parentPanel;

    public Page(String layoutString, ContentPanel rootPanel) {
        setupLayout(layoutString);
        parentPanel = rootPanel;
    }

    public Page(String layoutString) {
        setupLayout(layoutString);
    }

    private void setupLayout(String layoutString) {
        layout = new MigLayout(DEFAULT_CONSTRAINT, layoutString);
        setLayout(layout);
    }

    public String getPageId() {
        return this.getClass().getSimpleName();
    }

    public String getPageId(Class<? extends Page> p) {
        return p.getSimpleName();
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean isDefault() {
        return isDefault;
    }

    private void deriveParentIfNeeded() {
        if (parentPanel == null) {
            parentPanel = (ContentPanel) getParent();
        }
    }

    public void switchPage(String id) {
        deriveParentIfNeeded();
        parentPanel.switchPage(id);
    }

    public void sendNotification(Notification n) {
        deriveParentIfNeeded();
        parentPanel.sendNotification(n);
    }
}