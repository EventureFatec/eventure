package com.github.eventure.view.components;

import javax.swing.*;
import java.awt.*;
import com.github.eventure.view.components.ThemeManager;

public class SettingsPanel extends JPanel {
    public SettingsPanel(Runnable onThemeChanged) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(ThemeManager.getBackgroundColor());

        JCheckBox darkModeCheck = new JCheckBox("Modo Escuro");
        darkModeCheck.setSelected(ThemeManager.isDarkMode());

        darkModeCheck.addActionListener(e -> {
            ThemeManager.setDarkMode(darkModeCheck.isSelected());
            onThemeChanged.run(); 
        });

        add(darkModeCheck);
    }
}
