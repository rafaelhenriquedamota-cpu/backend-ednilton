package br.com.rodas.frontend.util;

import javax.swing.*;
import java.awt.*;

/**
 * Theme utility: defines colors and helper for button styling.
 * Uses FlatLaf look and feel in the main app.
 */
public class ThemeUtil {
    // primary palette
    public static final Color PRIMARY = new Color(10, 84, 162); // deep blue
    public static final Color ACCENT = new Color(219, 48, 54); // vibrant red
    public static final Color BG = new Color(245, 248, 251); // light background
    public static final Color PANEL = new Color(235, 242, 250);

    // button colors intuitive
    public static final Color BTN_ADD = new Color(34, 168, 95); // green
    public static final Color BTN_EDIT = new Color(255, 193, 7); // yellow
    public static final Color BTN_DELETE = new Color(220, 53, 69); // red
    public static final Color BTN_BACK = PRIMARY; // blue
    public static final Color BTN_LOGOUT = new Color(120, 130, 140); // gray

    public static void stylePrimaryLabel(JLabel lbl, float size) {
        lbl.setForeground(PRIMARY);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, size));
    }

    public static void styleButton(JButton btn, Color bgColor) {
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setPreferredSize(new Dimension(130,36));
        btn.setFont(btn.getFont().deriveFont(Font.BOLD,12f));
    }

    public static void applyBackground(JComponent c) {
        c.setBackground(BG);
    }
}
