package object;


import model.Cell;
import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import static controller.GameController.*;

public class Ghost extends JLabel implements Collisional {
    private Random random;
    private int xPos;
    private int yPos;
    private ImageIcon icon;
    private JTable table;
    private GameView view;
    ArrayList<String> colorList = new ArrayList<>() {{
        add("Blue");
        add("Orange");
        add("Pink");
        add("Red");
    }};
    public Ghost (int x, int y, JTable table, GameView view) {
        this.random = new Random();
        int randomIndex = random.nextInt(colorList.size());
        String randomColor = colorList.get(randomIndex);
        this.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pics/Ghost" + randomColor + ".png")));
        this.table = table;
        this.view = view;
        this.xPos = x;
        this.yPos = y;
    }
    public void move() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int index = random.nextInt(directions.length);
        int[] direction = directions[index];

        int dx = direction[0];
        int dy = direction[1];

        int x = (xPos + dx + table.getColumnCount()) % table.getColumnCount();
        int y = (yPos + dy + table.getRowCount()) % table.getRowCount();
        Cell destinationCell = cells[y][x];
        Collisional content = destinationCell.getContent();

        view.repaintCell(xPos, yPos);
        view.repaintCell(xPos + dx, yPos + dy);

        if (content instanceof Maze) {
            return;
        }
        this.xPos = x;
        this.yPos = y;
    }
    public int getXPos() { return xPos; }
    public int getYPos() { return yPos; }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = table.getColumnModel().getColumn(0).getWidth();
        g.drawImage(icon.getImage(), 0, 0, cellWidth, table.getRowHeight(yPos), null);
    }
}


