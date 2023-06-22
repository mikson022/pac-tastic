package object;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Point extends JLabel implements Collision {
    private final int xPos;
    private final int yPos;
    private final ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pics/Point.png")));
    private final JTable table;
    public Point(JTable table, int x, int y) {
        this.xPos = x;
        this.yPos = y;
        this.table = table;
    }
    public int getXPos() { return xPos; }
    public int getYPos() { return yPos; }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = table.getColumnModel().getColumn(0).getWidth();
        g.drawImage(icon.getImage(), 0, 0, cellWidth, table.getRowHeight(yPos), null);
    }
    @Override
    public boolean collidesWith(int x, int y) { return (xPos == x && yPos == y); }
}


