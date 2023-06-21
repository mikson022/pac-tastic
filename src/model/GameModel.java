package model;

import object.Pacman;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.Objects;
import java.util.Random;

public class GameModel {
    private final JTable table;
    private final Pacman pacman;
    public GameModel(Pacman pacman, JTable table) {
        this.pacman = pacman;
        this.table = table;
    }
    public int getRandomIntWithinRows() {
        Random random = new Random();
        return random.nextInt(table.getRowCount());
    }
    public int getRandomIntWithinColumns() {
        Random random = new Random();
        return random.nextInt(table.getColumnCount());
    }
}

