package com.github.eventure.view.windowing.layers;

import java.awt.CardLayout;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Queue;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.github.eventure.reflection.Reflection;
import com.github.eventure.view.windowing.Page;

import net.miginfocom.swing.MigLayout;

public class ContentLayer extends JPanel {
    private static final String PAGES_PACKAGE = "com.github.eventure.view.pages";
    private Queue<String> pagesHistory;
    private HashMap<String, Page> pagesAdded;
    private String currentPageId;
    private CardLayout layout;

    public ContentLayer() {
        setBackground(Color.PINK);
        setupContent();
        setupLayout();
        addPagesViaReflection();
    }

    private void setupContent() {
        JLayeredPane rootPanel = new JLayeredPane();
        MigLayout customLayout = new MigLayout();
        JPanel contentPanel = new JPanel(customLayout);
        contentPanel.setBackground(Color.BLUE);
        rootPanel.add(contentPanel, JLayeredPane.DEFAULT_LAYER);
        add(rootPanel);
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
                        System.out.println("Adding Page: " + p.getClass().getSimpleName());
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
