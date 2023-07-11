package object;


import model.Cell;
import model.SoundModel;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static controller.GameController.*;

public class Pacman extends JLabel implements Collisional {
    private final SoundModel move = new SoundModel("movement.wav");
    private final SoundModel maze = new SoundModel("maze.wav");
    private final SoundModel point = new SoundModel("point.wav");
    private int xPos;
    private int yPos;
    private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pics/PacmanRight.png")));
    private final JTable table;
    public Pacman(JTable table) {
        this.xPos = 0;
        this.yPos = 0;
        this.table = table;
    }
    public void move(int dx, int dy, String direction) {
        int x = (xPos + dx + table.getColumnCount()) % table.getColumnCount();
        int y = (yPos + dy + table.getRowCount()) % table.getRowCount();

        Cell destinationCell = cells[y][x];
        Collisional content = destinationCell.getContent();
        if (content instanceof Maze) {
            maze.playSound();
            return;
        }
        if (content instanceof Ghost) {
            this.xPos = 0;
            this.yPos = 0;
            subtractLife();
            table.repaint();
            return;
        }
        if (content instanceof Point) {
            destinationCell.removeObject(content);
            point.playSound();
            addScore();
        }
        move.playSound();
        this.icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pics/Pacman" + direction + ".png")));
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


