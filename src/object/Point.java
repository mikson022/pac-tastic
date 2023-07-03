package object;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Point extends JLabel implements Collisional {
    private final ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pics/Point.png")));
    private final JTable table;
    public Point(JTable table) {
        this.table = table;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = table.getColumnModel().getColumn(0).getWidth();
        g.drawImage(icon.getImage(), 0, 0, cellWidth, cellWidth, null);
    }
}


