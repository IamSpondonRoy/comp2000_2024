import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }

    class Canvas extends JPanel {
        Stage stage;
        Point mousePos;

        public Canvas() {
            setPreferredSize(new Dimension(1024, 720)); // Increase window size
            try {
                stage = StageReader.readStage("data/stage1.rvb");
            } catch (IOException e) {
                System.err.println("IOException occurred in Canvas constructor");
                System.exit(1);
            }
        }

        @Override
        public void paint(Graphics g) {
            mousePos = getMousePosition();
            stage.paint(g, mousePos);
            stage.grid.cellAtPoint(mousePos).ifPresent(cell -> {
                g.setColor(java.awt.Color.BLACK);
                g.drawString("Cell: " + cell.col + cell.row, 740, 20);
            });
        }
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            repaint();
        }
    }
}
