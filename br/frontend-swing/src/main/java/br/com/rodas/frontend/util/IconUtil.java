package br.com.rodas.frontend.util;

import javax.swing.*;

/**
 * Simple icon util using unicode symbols for portability.
 * You can replace with ImageIcon if you add image resources.
 */
public class IconUtil {
    public static Icon plus() { return new JLabel("＋").getIcon(); }
    // fallback: use text as button label
}
