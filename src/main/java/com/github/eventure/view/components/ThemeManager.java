package com.github.eventure.view.components;

import java.awt.Color;

public class ThemeManager {
    private static boolean darkMode = false;

    private static final Color LIGHT_BG = new Color(0xe5d8fd);
    private static final Color DARK_BG = new Color(0x222223);

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void setDarkMode(boolean enabled) {
        darkMode = enabled;
    }

    public static Color getBackgroundColor() {
        return darkMode ? DARK_BG : LIGHT_BG;
    }

    // Pode ter também cores de texto, botão, etc
    public static Color getForegroundColor() {
        return darkMode ? Color.WHITE : Color.BLACK;
    }
}
