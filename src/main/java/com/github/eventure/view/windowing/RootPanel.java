package com.github.eventure.view.windowing;

import java.awt.CardLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Queue;

import javax.swing.JPanel;

import com.github.eventure.reflection.Reflection;

public class RootPanel extends JPanel {
    // TODO: Add notification support and layered panes (content, modal and pop-ups)
    private static final String PAGES_PACKAGE = "com.github.eventure.view.pages";
    private Queue<String> pagesHistory;
    private HashMap<String, Page> pagesAdded;
    private String currentPageId;
    private CardLayout layout;

    public RootPanel() {
        setupLayout();
        addPagesViaReflection();
    }

    private void setupLayout() {
        layout = new CardLayout();
        setLayout(layout);
    }

    private void addPagesViaReflection() {
        try {
            var classes = Reflection.getClassesInPackage(PAGES_PACKAGE);

            for (var cls : classes) {
                if (Page.class.isAssignableFrom(cls)) {
                    Page p = derivePageFromClass(cls);
                    if (p != null)
                        addPage(p);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Page derivePageFromClass(Class<?> c) {
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

    public <T extends Page> void addPage(T p) {
        if (pagesAdded == null) {
            pagesAdded = new HashMap<>();
        }

        var pageId = p.getPageId();
        if (currentPageId == null)
            currentPageId = pageId;
        pagesAdded.put(pageId, p);
        add(p, pageId);
    }

    public <T extends Page> void switchPage(T p) {
        switchPage(p.getPageId());
    }

    public void switchPage(String id) {
        layout.show(this, id);
        currentPageId = id;
    }

    public boolean canGoBackInHistory() {
        return pagesHistory.size() > 1;
    }

    public void goBackInHistory() {
        if (canGoBackInHistory()) {
            switchPage(pagesHistory.poll());
        }
    }
}
