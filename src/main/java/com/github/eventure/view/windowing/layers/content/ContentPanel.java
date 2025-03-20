package com.github.eventure.view.windowing.layers.content;

import java.awt.CardLayout;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JPanel;

import com.github.eventure.reflection.Reflection;
import com.github.eventure.view.windowing.RootPanel;
import com.github.eventure.view.windowing.basic.Notification;
import com.github.eventure.view.windowing.basic.Page;

public class ContentPanel extends JPanel {
    private static final String PAGES_PACKAGE = "com.github.eventure.view.pages";
    private CardLayout panelLayout;
    private ContentLayer parentLayer;
    private String defaultPageId;

    public ContentPanel(ContentLayer parent) {
        parentLayer = parent;
        setupLayout();
        addPagesViaReflection();
        showDefault();
    }

    private void setupLayout() {
        panelLayout = new CardLayout();
        setLayout(panelLayout);
    }

    private void addPagesViaReflection() {
        try {
            var classes = Reflection.getClassesInPackage(PAGES_PACKAGE);

            for (var cls : classes) {
                if (Page.class.isAssignableFrom(cls)) {
                    Page p = derivePageFromClass(cls);
                    if (p != null)
                        System.out.println("Adding Page: " + p.getClass().getSimpleName());
                        addPage(p);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Page derivePageFromClass(Class<?> c) {
        try {
            return (Page) c.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException
                | IllegalAccessException
                | InstantiationException
                | NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T extends Page> void addPage(T p) {
        add(p, p.getPageId());
        if (p.isDefault()) {
            defaultPageId = p.getPageId();
        }
    }

    private void showDefault() {
        if (defaultPageId != null) {
            switchPage(defaultPageId);
        }
    }

    public void switchPage(String pageId) {
        panelLayout.show(this, pageId);
    }

    public void sendNotification(Notification n) {
        parentLayer.sendNotification(n);
    }
}
