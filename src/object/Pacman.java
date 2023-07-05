package object;


import model.Cell;
import model.SoundModel;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static controller.GameController.*;

public class Pacman extends JLabel implements Collisional {
    private final SoundModel mazeSound = new SoundModel("maze.wav");
    private final SoundModel pointSound = new SoundModel("point.wav");
    private final SoundModel moveSound = new SoundModel("movement.wav");
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
            mazeSound.playSound();
            return;
        }
        if (content instanceof Point) {
            pointSound.playSound();
            addScore();
            destinationCell.removeObject(content);
        }
        moveSound.playSound();
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


