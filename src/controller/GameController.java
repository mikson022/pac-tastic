package controller;

import model.GameModel;
import model.ThreadModel;
import object.Pacman;
import object.Point;
import view.GameView;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private final Pacman pacman;
    private final JTable table;
    private final JFrame frame;
    public static ArrayList<Point> points;
    public GameController(int rows, int columns) {
        this.frame = new JFrame();
        table = new JTable(rows, columns) {
            @Override
            public void changeSelection(int row, int column, boolean toggle, boolean extend) {
            }
        };
        pacman = new Pacman(table);
        points = new ArrayList<>();
        model = new GameModel(pacman, table);
        view = new GameView(frame, pacman, table);

        ThreadModel timeCounter = new ThreadModel() {
            private volatile boolean running = true;
            private int minutes;
            private int seconds;
            private String formattedTime;
            @Override
            public void run() {
                while (running) {
                    try {
                        Thread.sleep(1000);
                        incrementSeconds();
                        updateFormattedTime();
                        view.setTimeOnPanel(formattedTime);
                        generatePoint();
                        updateView();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            public void stopCounting() {
                running = false;
            }
            private void generatePoint() {
                if (seconds == 0) { return; }
                if (seconds % 5 == 0) {
                    int x = model.getRandomIntWithinColumns();
                    int y = model.getRandomIntWithinRows();
                    boolean pointExists = false;
                    for (Point point : points) {
                        if (point.getXPos() == x && point.getYPos() == y) {
                            pointExists = true;
                            break;
                        }
                    }
                    if (!pointExists) {
                        Point newPoint = new Point(table, x, y);
                        points.add(newPoint);
                    }
                }
            }

            private void incrementSeconds() {
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    minutes++;
                }
            }
            private void updateFormattedTime() {
                this.formattedTime = String.format("%02d:%02d", minutes, seconds);
            }
        };

        timeCounter.start();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
                        updateView();
                        model.playSound("movement.wav");
                    }
                    case KeyEvent.VK_RIGHT -> {
                        pacman.move(1, 0, "Right");
                        updateView();
                        model.playSound("movement.wav");
                    }
                    case KeyEvent.VK_UP -> {
                        pacman.move(0, -1, "Up");
                        updateView();
                        model.playSound("movement.wav");
                    }
                    case KeyEvent.VK_DOWN -> {
                        pacman.move(0, 1, "Down");
                        updateView();
                        model.playSound("movement.wav");
                    }
                }
            }
        });
    }
    private void updateView() {
        table.repaint();
        frame.repaint();
    }
}
