import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class Main extends JFrame {
    public static void main(String[] args) throws Exception {
        Main window = new Main();
        window.run();
    }

    class Canvas extends JPanel implements MouseMotionListener{
        Grid grid = new Grid();
        LinkedList<Point> trail_mouse = new LinkedList<>();
        int trail_size = 100;
        int circle_size = 15;

        public Canvas() {
            setPreferredSize(new Dimension(720, 720));
            addMouseMotionListener(this);
        }

        @Override
        public void paint(Graphics g) {
            // Point mousePos = getMousePosition();
            // if (mousePos != null) {
            //     if (trail_mouse.size() >= trail_size) {
            //       trail_mouse.removeFirst();
            //     }
            //     trail_mouse.addLast(mousePos);
            // }
            grid.paint(g, null);
            drawMouseTrail(g);
        }

        private void drawMouseTrail(Graphics g) {
            for (int item = 0; item < trail_mouse.size(); item+=1) {
                Point p = trail_mouse.get(item);
                int transperancy = (int) (255 * ((double) (item + 1) / trail_size)); // why double feels better but not float
                g.setColor(new Color(10, 70, 10, transperancy));
                g.fillOval(p.x - circle_size / 2, p.y - circle_size / 2, circle_size, circle_size);
            }
        }
        
        @Override
        public void mouseMoved(MouseEvent e) {
            Point mousePos = e.getPoint();
            if (trail_mouse.size() >= trail_size) {
              trail_mouse.removeFirst();
            }
            trail_mouse.addLast(mousePos);
            repaint();
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            mouseMoved(e);
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
        }
    }
}
// Complete part2