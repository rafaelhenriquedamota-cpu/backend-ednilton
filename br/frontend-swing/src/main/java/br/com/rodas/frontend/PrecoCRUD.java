package br.com.rodas.frontend;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * CRUD de preços que sincroniza com PricesStore para manter os preços visíveis no MenuPrincipal.
 */
public class PrecoCRUD extends BaseCRUDFrame {
    public PrecoCRUD() {
        super("Gerenciador de Preços de Combustível", new String[] { "ID","Descrição","Valor (R$)","Data Alteração","Hora Alteração" });

        // preenche a tabela com os preços do PricesStore
        model.setRowCount(0);
        int id = 1;
        for (Map.Entry<String, String> e : PricesStore.getAll().entrySet()) {
            model.addRow(new Object[] { id++, e.getKey(), e.getValue(), LocalDate.now().toString(), LocalTime.now().withNano(0).toString() });
        }

        // janela deve fechar (dispose) e acionar listeners (MenuPrincipal atualiza)
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
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
            // também atualiza PricesStore se for um combustível conhecido ou adiciona novo
            PricesStore.set(desc.getText(), valor.getText());
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
            // atualiza PricesStore também
            PricesStore.set(desc.getText(), valor.getText());
        }
    }
}
