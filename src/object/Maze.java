package object;

import javax.swing.*;
import java.awt.*;

public class Maze extends JLabel implements Collision {
    private final int xPos;
    private final int yPos;
    private final JTable table;
    public Maze(JTable table, int x, int y) {
        this.setOpaque(true);
        this.setBackground(Color.RED);
        this.xPos = x;
        this.yPos = y;
        this.table = table;
    }
    public int getXPos() { return xPos; }
    public int getYPos() { return yPos; }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = table.getColumnModel().getColumn(0).getWidth();
        int cellHeight = table.getRowHeight(yPos);
        g.setColor(Color.RED);
        g.fillRect(0, 0, cellWidth, cellHeight);
    }
    @Override
    public boolean collidesWith(int x, int y) {
        return (xPos == x && yPos == y);
    }
}
