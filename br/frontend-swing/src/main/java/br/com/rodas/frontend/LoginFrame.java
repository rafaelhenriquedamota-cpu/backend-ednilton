package br.com.rodas.frontend;

import br.com.rodas.frontend.util.ThemeUtil;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        super("Login - Sistema Rodas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420,260);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        ThemeUtil.applyBackground(getContentPane());

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(16,16,16,16));
        panel.setBackground(ThemeUtil.BG);

        JLabel title = new JLabel("Entrar"); ThemeUtil.stylePrimaryLabel(title, 20f);
        panel.add(title, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(2,2,8,8));
        center.setBorder(BorderFactory.createEmptyBorder(12,0,12,0));
        center.setBackground(ThemeUtil.BG);
        center.add(new JLabel("Usuário:"));
        JTextField txtUser = new JTextField();
        center.add(txtUser);
        center.add(new JLabel("Senha:"));
        JPasswordField txtPass = new JPasswordField();
        center.add(txtPass);
        panel.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(ThemeUtil.BG);
        JButton btnLogin = new JButton("Entrar"); ThemeUtil.styleButton(btnLogin, ThemeUtil.BTN_BACK);
        JButton btnGuest = new JButton("Entrar como Convidado"); ThemeUtil.styleButton(btnGuest, ThemeUtil.BTN_ADD);
        bottom.add(btnGuest); bottom.add(btnLogin);
        panel.add(bottom, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        btnLogin.addActionListener(e -> {
            // no real validation for now
            MenuPrincipal m = new MenuPrincipal();
            m.setVisible(true);
            this.dispose();
        });
        btnGuest.addActionListener(e -> {
            MenuPrincipal m = new MenuPrincipal();
            m.setVisible(true);
            this.dispose();
        });
    }

    public static void main(String[] args) {
        // apply FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) { /* fallback */ }
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
