package controller;

import model.GameModel;
import object.Pacman;
import view.GameView;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private final Pacman pacman;
    private final JTable table;
    public GameController(int rows, int columns) {
        table = new JTable(rows, columns) {
            @Override
            public void changeSelection(int row, int column, boolean toggle, boolean extend) {
            }
        };
        pacman = new Pacman(table);
        model = new GameModel(pacman, table);
        view = new GameView(pacman, table);
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        table.setFocusable(true);
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
            private void handleKeyPress(int keyCode) {
                switch (keyCode) {
                    case KeyEvent.VK_LEFT -> {
                        pacman.move(-1, 0, "Left");
                        view.updateView();
                    }
                    case KeyEvent.VK_RIGHT -> {
                        pacman.move(1, 0, "Right");
                        view.updateView();
                    }
                    case KeyEvent.VK_UP -> {
                        pacman.move(0, -1, "Up");
                        view.updateView();
                    }
                    case KeyEvent.VK_DOWN -> {
                        pacman.move(0, 1, "Down");
                        view.updateView();
                    }
                }
            }
        });

    }





}
