import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;

public class PainelDesenhavel extends JPanel {

    BufferedImage imagem;
    Graphics2D g2;
    Graphics g;

    Color mudaCor = Color.BLACK;

    public PainelDesenhavel() {
        setBackground(Color.white);
        imagem = new BufferedImage(900, 700, BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) imagem.getGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 900, 700);
    }

    public void mudaCor(Color c) {
        this.mudaCor = c;
    }

    public void desenhar(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(this.mudaCor);
        g.fillOval(x, y, 15, 15);
        g2.setColor(this.mudaCor);
        g2.fillOval(x, y, 15, 15);
    }
}
