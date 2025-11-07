package br.com.rodas.frontend;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class PessoaCRUD extends BaseCRUDFrame {
    public PessoaCRUD() { super("Pessoas", new String[] { "ID","Descrição","Valor (R$)","Data Alteração","Hora Alteração" }); 
        // sample data
        model.addRow(new Object[] {1, "Exemplo 1", "10.00", LocalDate.now().toString(), LocalTime.now().withNano(0).toString() });
        model.addRow(new Object[] {2, "Exemplo 2", "20.50", LocalDate.now().toString(), LocalTime.now().withNano(0).toString() });
    }

    @Override
    protected void onAdd() {
        JTextField desc = new JTextField();
        JTextField valor = new JTextField();
        Object[] fields = { "Descrição:", desc, "Valor (R$):", valor };
        int opt = JOptionPane.showConfirmDialog(this, fields, "Adicionar", JOptionPane.OK_CANCEL_OPTION);
        if (opt == JOptionPane.OK_OPTION) {
            int id = model.getRowCount() + 1;
            model.addRow(new Object[] { id, desc.getText(), valor.getText(), LocalDate.now().toString(), LocalTime.now().withNano(0).toString() });
        }
    }

    @Override
    protected void onEdit() {
        int row = table.getSelectedRow();
        if (row == -1) { JOptionPane.showMessageDialog(this, "Selecione uma linha para editar."); return; }
        String curDesc = model.getValueAt(row,1).toString();
        String curVal = model.getValueAt(row,2).toString();
        JTextField desc = new JTextField(curDesc);
        JTextField valor = new JTextField(curVal);
        Object[] fields = { "Descrição:", desc, "Valor (R$):", valor };
        int opt = JOptionPane.showConfirmDialog(this, fields, "Editar", JOptionPane.OK_CANCEL_OPTION);
        if (opt == JOptionPane.OK_OPTION) {
            model.setValueAt(desc.getText(), row, 1);
            model.setValueAt(valor.getText(), row, 2);
            model.setValueAt(LocalDate.now().toString(), row, 3);
            model.setValueAt(LocalTime.now().withNano(0).toString(), row, 4);
        }
    }
}
