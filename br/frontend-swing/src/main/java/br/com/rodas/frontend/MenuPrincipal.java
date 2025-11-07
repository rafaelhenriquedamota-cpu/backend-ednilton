package br.com.rodas.frontend;

import br.com.rodas.frontend.util.ThemeUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

public class MenuPrincipal extends JFrame {
    private JPanel pumpsContainer;

    public MenuPrincipal() {
        super("Menu Principal - Sistema Rodas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(960,600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(100,70));
        header.setBackground(ThemeUtil.PRIMARY);
        JLabel title = new JLabel("Sistema de Gerenciamento de Posto", SwingConstants.LEFT);
        title.setForeground(Color.WHITE);
        title.setFont(title.getFont().deriveFont(Font.BOLD,18f));
        title.setBorder(BorderFactory.createEmptyBorder(10,16,10,10));
        header.add(title, BorderLayout.WEST);

        JButton btnLogout = new JButton("Sair"); ThemeUtil.styleButton(btnLogout, ThemeUtil.BTN_LOGOUT);
        btnLogout.setPreferredSize(new Dimension(100,36));
        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        JPanel right = new JPanel(); right.setOpaque(false); right.add(btnLogout);
        header.add(right, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        JPanel left = new JPanel();
        left.setPreferredSize(new Dimension(220,0));
        left.setBackground(ThemeUtil.PANEL);
        left.setLayout(new GridLayout(0,1,8,8));
        left.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        JButton bPreco = new JButton("Preços"); ThemeUtil.styleButton(bPreco, ThemeUtil.BTN_BACK);
        JButton bProduto = new JButton("Produtos"); ThemeUtil.styleButton(bProduto, ThemeUtil.BTN_BACK);
        JButton bPessoa = new JButton("Pessoas"); ThemeUtil.styleButton(bPessoa, ThemeUtil.BTN_BACK);
        JButton bContato = new JButton("Contatos"); ThemeUtil.styleButton(bContato, ThemeUtil.BTN_BACK);
        JButton bCusto = new JButton("Custos"); ThemeUtil.styleButton(bCusto, ThemeUtil.BTN_BACK);
        JButton bEstoque = new JButton("Estoque"); ThemeUtil.styleButton(bEstoque, ThemeUtil.BTN_BACK);
        JButton bAcesso = new JButton("Acessos"); ThemeUtil.styleButton(bAcesso, ThemeUtil.BTN_BACK);

        left.add(bPreco); left.add(bProduto); left.add(bPessoa); left.add(bContato);
        left.add(bCusto); left.add(bEstoque); left.add(bAcesso);

        add(left, BorderLayout.WEST);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(ThemeUtil.BG);

        // topo do centro com título e botão atualizar
        JPanel topCenter = new JPanel(new BorderLayout());
        topCenter.setOpaque(false);
        JLabel welcome = new JLabel("Bem-vindo ao sistema", SwingConstants.CENTER);
        welcome.setFont(welcome.getFont().deriveFont(20f));
        welcome.setBorder(BorderFactory.createEmptyBorder(20,20,8,20));
        topCenter.add(welcome, BorderLayout.CENTER);

        JButton btnRefresh = new JButton("Atualizar preços"); ThemeUtil.styleButton(btnRefresh, ThemeUtil.BTN_BACK);
        btnRefresh.setPreferredSize(new Dimension(160,34));
        btnRefresh.addActionListener(e -> refreshPumps());
        JPanel refreshHolder = new JPanel(); refreshHolder.setOpaque(false); refreshHolder.add(btnRefresh);
        topCenter.add(refreshHolder, BorderLayout.EAST);

        center.add(topCenter, BorderLayout.NORTH);

        // container de bombas
        pumpsContainer = new JPanel();
        pumpsContainer.setOpaque(false);
        pumpsContainer.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        pumpsContainer.setLayout(new GridLayout(1,0,12,12));

        // criar uma bomba para cada combustível cadastrado no PricesStore
        for (Map.Entry<String, String> entry : PricesStore.getAll().entrySet()) {
            pumpsContainer.add(new FuelPumpPanel(entry.getKey()));
        }

        JScrollPane scroll = new JScrollPane(pumpsContainer);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);

        center.add(scroll, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        // listeners dos botões do menu — abrem janelas e atualizam bombas quando o Preço for fechado
        bPreco.addActionListener(e -> {
            PrecoCRUD preco = new PrecoCRUD();
            preco.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    refreshPumps();
                }
            });
            preco.setVisible(true);
        });
        bProduto.addActionListener(e -> { new ProdutoCRUD().setVisible(true); });
        bPessoa.addActionListener(e -> { new PessoaCRUD().setVisible(true); });
        bContato.addActionListener(e -> { new ContatoCRUD().setVisible(true); });
        bCusto.addActionListener(e -> { new CustoCRUD().setVisible(true); });
        bEstoque.addActionListener(e -> { new EstoqueCRUD().setVisible(true); });
        bAcesso.addActionListener(e -> { new AcessoCRUD().setVisible(true); });
    }

    private void refreshPumps() {
        pumpsContainer.removeAll();
        for (String key : PricesStore.getAll().keySet()) {
            pumpsContainer.add(new FuelPumpPanel(key));
        }
        pumpsContainer.revalidate();
        pumpsContainer.repaint();
    }
}
