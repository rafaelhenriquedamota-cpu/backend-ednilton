package br.com.rodas.frontend;

import br.com.rodas.frontend.util.ThemeUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Base frame for CRUD screens: includes Add/Edit/Delete, Back and Logout buttons.
 */
public abstract class BaseCRUDFrame extends JFrame {
    protected DefaultTableModel model;
    protected JTable table;

    public BaseCRUDFrame(String title, String[] columnNames) {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(820,520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));
        ThemeUtil.applyBackground(getContentPane());

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(ThemeUtil.PANEL);
        topBar.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));

        JLabel titleLbl = new JLabel(title);
        ThemeUtil.stylePrimaryLabel(titleLbl, 16f);
        topBar.add(titleLbl, BorderLayout.WEST);

        JPanel topButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT,6,0));
        topButtons.setOpaque(false);
        JButton btnBack = new JButton("Voltar ao Menu"); ThemeUtil.styleButton(btnBack, ThemeUtil.BTN_BACK);
        JButton btnLogout = new JButton("Sair / Trocar Conta"); ThemeUtil.styleButton(btnLogout, ThemeUtil.BTN_LOGOUT);
        topButtons.add(btnBack); topButtons.add(btnLogout);
        topBar.add(topButtons, BorderLayout.EAST);

        add(topBar, BorderLayout.NORTH);

        model = new DefaultTableModel(columnNames, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        add(sp, BorderLayout.CENTER);

        JPanel actionBar = new JPanel(new FlowLayout(FlowLayout.LEFT,10,8));
        actionBar.setOpaque(false);
        JButton btnAdd = new JButton("Adicionar"); ThemeUtil.styleButton(btnAdd, ThemeUtil.BTN_ADD);
        JButton btnEdit = new JButton("Editar"); ThemeUtil.styleButton(btnEdit, ThemeUtil.BTN_EDIT);
        JButton btnDel = new JButton("Excluir"); ThemeUtil.styleButton(btnDel, ThemeUtil.BTN_DELETE);

        actionBar.add(btnAdd); actionBar.add(btnEdit); actionBar.add(btnDel);
        add(actionBar, BorderLayout.SOUTH);

        // default actions
        btnAdd.addActionListener(e -> onAdd());
        btnEdit.addActionListener(e -> onEdit());
        btnDel.addActionListener(e -> onDelete());
        btnBack.addActionListener(e -> {
            dispose();
            new MenuPrincipal().setVisible(true);
        });
        btnLogout.addActionListener(e -> {
            // close and show login
            dispose();
            // try to dispose other frames by opening login
            new LoginFrame().setVisible(true);
        });
    }

    protected abstract void onAdd();
    protected abstract void onEdit();
    protected void onDelete() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir.");
            return;
        }
        int opt = JOptionPane.showConfirmDialog(this, "Confirma exclusão?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) model.removeRow(row);
    }
}
