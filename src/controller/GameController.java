package controller;

import model.GameModel;
import model.MazeGenerator;
import model.ThreadModel;
import model.Cell;
import object.Collisional;
import object.Maze;
import object.Pacman;
import object.Point;
import view.GameView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private final Pacman pacman;
    private final JTable table;
    private final JFrame frame;
    public static CopyOnWriteArrayList<Collisional> collisions;
    public static Cell[][] cells;
    private static int score;
    public GameController(int rows, int columns) {
        this.frame = new JFrame();
        table = new JTable(rows, columns) {
            @Override
            public void changeSelection(int row, int column, boolean toggle, boolean extend) {
            }
        };

        cells = new Cell[table.getRowCount()][table.getColumnCount()];
        for (int y = 0; y < table.getRowCount(); y++) {
            for (int x = 0; x < table.getColumnCount(); x++) {
                cells[y][x] = new Cell();
            }
        }

        pacman = new Pacman(table);
        model = new GameModel(pacman, table);
        view = new GameView(frame, pacman, table);

        collisions = new CopyOnWriteArrayList<>();
        score = 0;
        {
            //MazeGeneration
            MazeGenerator mazeGenerator = new MazeGenerator(rows, columns);
            boolean[][] mazeLayout = mazeGenerator.generateMaze();

            Maze maze = new Maze(table);
            int counter = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    counter += 1;
                    if (mazeLayout[i][j]) {
                        if (counter % 3 != 0){
                            cells[i][j].addObject(maze);
                        }
                    }
                }
            }
            //MazeGeneration
        }
        ThreadModel timeCounter = new ThreadModel() {
            private Point point = new Point(table);
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
                        view.setScoreOnPanel(score);
                        generatePoint();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            public void stopCounting() { running = false; }
            private void generatePoint() {
                if (seconds == 0) { return; }
                if (seconds % 5 == 0) {
                    createPoint();
                }
            }
            private void createPoint() {
                int x = model.getRandomIntWithinColumns();
                int y = model.getRandomIntWithinRows();
                Collisional content = cells[y][x].getContent();
                if (content instanceof Maze) { createPoint(); }
                if (content instanceof Point) { createPoint(); }
                else {
                    cells[y][x].addObject(point);
                    view.repaintCell(x, y);
                }
            }
            private void incrementSeconds() {
                seconds++;
                if (seconds >= 60) { seconds = 0; minutes++; }
            }
            private void updateFormattedTime() { this.formattedTime = String.format("%02d:%02d", minutes, seconds); }
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
                int dx = 0;
                int dy = 0;
                String direction = "";

                switch (keyCode) {
                    case KeyEvent.VK_LEFT -> {
                        dx = -1;
                        direction = "Left";
                    }
                    case KeyEvent.VK_RIGHT -> {
                        dx = 1;
                        direction = "Right";
                    }
                    case KeyEvent.VK_UP -> {
                        dy = -1;
                        direction = "Up";
                    }
                    case KeyEvent.VK_DOWN -> {
                        dy = 1;
                        direction = "Down";
                    }
                }
                int currentX = pacman.getXPos();
                int currentY = pacman.getYPos();

                pacman.move(dx, dy, direction);

                int nextX = pacman.getXPos();
                int nextY = pacman.getYPos();

                view.repaintCell(currentX, currentY);
                view.repaintCell(nextX, nextY);
            }
        });
    }
    public static void addScore() { score += 10; }
}
