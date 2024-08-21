import java.awt.Graphics;
import java.awt.Point;
import java.util.Optional;

public class Grid {
    Cell[][] cells = new Cell[20][20];
  
    public Grid() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(colToLabel(i), j, 10 + Cell.size * i, 10 + Cell.size * j);
            }
        }
    }

    private char colToLabel(int col) {
        return (char) (col + Character.valueOf('A'));
    }

    private int labelToCol(char col) {
        return (int) (col - Character.valueOf('A'));
    }

    public void paint(Graphics g, Point mousePos) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].paint(g, mousePos);
            }
        }
    }

    public Optional<Cell> cellAtColRow(int c, int r) {
        if (c < 0 || c >= cells.length || r < 0 || r >= cells[c].length) {
            return Optional.empty();
        }
        return Optional.ofNullable(cells[c][r]);
    }

    public Optional<Cell> cellAtColRow(char c, int r) {
        return cellAtColRow(labelToCol(c), r);
    }

    public Optional<Cell> cellAtPoint(Point p) {
        if (p == null) {
            return Optional.empty();
        }

        int col = (p.x - 10) / Cell.size;
        int row = (p.y - 10) / Cell.size;

        if (col < 0 || col >= cells.length || row < 0 || row >= cells[col].length) {
            return Optional.empty();
        }

        return Optional.ofNullable(cells[col][row]);
    }
}
