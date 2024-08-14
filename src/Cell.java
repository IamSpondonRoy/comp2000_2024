import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Cell {
    int x;
    int y;
    static int size = 35;

    public Cell(int inX, int inY) {
        x = inX;
        y = inY;
    }


    public void paint(Graphics g, Point mousePos) {
        if (contains(mousePos)) {
            g.setColor(Color.gray);
        } else {
            g.setColor(Color.white);
        }
        g.fillRect(x, y, size, size);
        g.setColor(Color.green);
        g.drawRect(x, y, size, size);
    }

    public boolean contains(Point p) {
        if (p != null) {
            return x < p.x && x + size > p.x && y < p.y && y + size > p.y;
        } else {
            return false;
        }
    }
}
