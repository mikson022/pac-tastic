package object;

import javax.swing.*;
import java.awt.*;

public class Maze extends JLabel implements Collisional {
    private final JTable table;
    public Maze(JTable table) {
        this.table = table;
        this.setOpaque(true);
        this.setBackground(Color.RED);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = table.getColumnModel().getColumn(0).getWidth();
        g.setColor(Color.RED);
        g.fillRect(0, 0, cellWidth, cellWidth);
    }
}
