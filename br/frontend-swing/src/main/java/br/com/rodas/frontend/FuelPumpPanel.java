package br.com.rodas.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * Painel que desenha uma "bomba" estilizada e mostra o preço atual.
 */
public class FuelPumpPanel extends JPanel {
    private final String fuelName;

    public FuelPumpPanel(String fuelName) {
        this.fuelName = fuelName;
        setPreferredSize(new Dimension(260, 160));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // fundo levemente arredondado (card)
            int pad = 8;
            RoundRectangle2D card = new RoundRectangle2D.Float(pad, pad, w - pad*2, h - pad*2, 14, 14);
            g2.setColor(new Color(245, 247, 249));
            g2.fill(card);

            // corpo da bomba
            int bx = pad + 12;
            int by = pad + 12;
            int bw = 90;
            int bh = h - pad*4 - 12;
            g2.setColor(new Color(20, 90, 160)); // azul escuro
            g2.fillRoundRect(bx, by, bw, bh, 8, 8);

            // visor (retângulo claro)
            int vx = bx + 10;
            int vy = by + 12;
            int vw = bw - 20;
            int vh = 36;
            g2.setColor(new Color(230, 240, 250));
            g2.fillRoundRect(vx, vy, vw, vh, 6, 6);

            // etiqueta do nome do combustível (ao lado)
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14f));
            g2.setColor(new Color(15, 50, 100));
            g2.drawString(fuelName, bx + bw + 18, by + 28);

            // preço grande (puxado do PricesStore)
            String price = PricesStore.get(fuelName);
            if (price == null) price = "--.--";
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 22f));
            g2.drawString("R$ " + price, bx + bw + 18, by + 60);

            // bico / mangueira estilizados
            int nx = bx + bw - 8;
            int ny = by + bh/2 - 6;
            g2.setStroke(new BasicStroke(6f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.setColor(new Color(80, 80, 80));
            g2.drawLine(nx, ny, nx + 40, ny - 10);
            g2.drawLine(nx + 40, ny - 10, nx + 70, ny - 30);
            g2.setStroke(new BasicStroke(1f));

            // pequeno destaque do botão/seleção
            g2.setColor(new Color(255, 200, 0));
            g2.fillOval(bx + bw/2 - 10, by + bh - 28, 20, 20);

            // borda sutil do card
            g2.setColor(new Color(200, 210, 218));
            g2.draw(card);

        } finally {
            g2.dispose();
        }
    }
}
