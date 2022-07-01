
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Container;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tela extends JFrame {
    PainelDesenhavel drawPanel;
    JLabel rodape;
    JPanel buttonsPanel;
    JButton botaoCor;
    JFileChooser chooser;
    String choosertitle;
    DefaultListModel<String> model;

    /*
     * Paleta de cores
     */
    Color[] colors = new Color[] {
            Color.BLACK,
            Color.BLUE,
            Color.GRAY,
            Color.GREEN,
            Color.PINK,
            Color.ORANGE,
            Color.RED,
            Color.YELLOW,
            Color.WHITE
    };

    ActionListener buttons = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton bt = (JButton) e.getSource();
            drawPanel.mudaCor(bt.getBackground());
        }
    };

    /*
     * clique e rodapé
     */
    MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            System.out.println(e.getX() + " " + e.getY());
            drawPanel.desenhar(e.getX(), e.getY());
            if (Objects.equals(rodape.getText(), "Imagem Original")) {
                rodape.setText("Imagem Modificada");
            }
        }
    };

    /*
     * salvar imagem desenhada
     */
    ActionListener saveFile = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String locale;
            String name;

            if (Objects.equals(rodape.getText(), "Imagem Modificada")) {
                rodape.setText("Imagem Original");

                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(getContentPane()) == JFileChooser.APPROVE_OPTION) {
                    name = fileChooser.getSelectedFile().getName();
                    locale = fileChooser.getCurrentDirectory().toString();

                    try {
                        File outputfile = new File(locale + '/' + name + ".jpg");
                        ImageIO.write(drawPanel.imagem, "jpg", outputfile);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    };

    /*
     * Criar e rodar
     */
    public void runPaint() {
        setSize(900, 700);
        drawPanel = new PainelDesenhavel();
        getContentPane().add(drawPanel, BorderLayout.CENTER);
        drawPanel.addMouseMotionListener(ma);

        Container container = getContentPane();

        JPanel leftFlow = new JPanel(new FlowLayout());
        buttonsPanel = new JPanel(new GridLayout(0, 1));
        for (Color cor : colors) {
            botaoCor = new JButton("   ");
            botaoCor.addActionListener(buttons);
            botaoCor.setBackground(cor);
            buttonsPanel.add(botaoCor);
        }
        leftFlow.add(buttonsPanel);
        getContentPane().add(leftFlow, BorderLayout.WEST);

        rodape = new JLabel("Imagem Original");
        container.add(rodape, BorderLayout.SOUTH);

        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        itemSalvar.addActionListener(saveFile);

        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener((e) -> {
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });

        menuArquivo.add(itemSalvar);
        menuArquivo.add(itemSair);

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(Tela.this, "Programacao II\nSegunda Avaliação!");
        });

        menuAjuda.add(itemSobre);
        menuAjuda.add(itemSobre);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuArquivo);
        menuBar.add(menuAjuda);
        setJMenuBar(menuBar);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*
     * RUN
     */
    public static void main(String[] args) {
        Tela tp = new Tela();
        tp.runPaint();
    }

}
