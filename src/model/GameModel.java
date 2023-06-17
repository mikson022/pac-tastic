package model;

import object.Pacman;
import javax.swing.*;

public class GameModel {
    private final JTable table;
    private final Pacman pacman;

    public GameModel(Pacman pacman, JTable table) {
        this.pacman = pacman;
        this.table = table;
    }
}

