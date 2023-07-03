package controller;

import model.GameModel;
import model.MazeGenerator;
import model.ThreadModel;
import model.Cell;
import object.Collisional;
import object.Maze;
import object.Pacman;
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

            int counter = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    counter += 1;
                    if (mazeLayout[i][j]) {
                        if (counter % 3 != 0){
                            Maze maze = new Maze(table);
                            cells[i][j].addObject(maze);
                        }
                    }
                }
            }
            //MazeGeneration
        }
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
                        //generatePoint();
                        updateView();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            public void stopCounting() { running = false; }
            /*
            private void generatePoint() {
                if (seconds == 0) { return; }
                if (seconds % 5 == 0) {
                    int x = model.getRandomIntWithinColumns();
                    int y = model.getRandomIntWithinRows();
                    boolean collides = false;

                    for (Collision collision : collisions) {
                        if (collision.collidesWith(x, y)) {
                            collides = true;
                            break;
                        }
                    }
                    if (!collides) {
                        Point newPoint = new Point(table, x, y);
                        points.add(newPoint);
                        collisions.add(newPoint);
                    }
                }
            }
            */
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
                pacman.move(dx, dy, direction);
                updateView();
            }
        });
    }
    public static void addPoint() { score += 10; }
    private void updateView() {
        view.setScoreOnPanel(score);
        table.repaint();
        frame.repaint();
    }
}
