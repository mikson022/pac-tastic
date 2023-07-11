package controller;

import model.GameModel;
import model.PathGenerator;
import model.ThreadModel;
import model.Cell;
import object.*;
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
    public static CopyOnWriteArrayList<Ghost> ghosts;
    public static Cell[][] cells;
    private static int score;
    private static int lives;
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

        ghosts = new CopyOnWriteArrayList<>();
        score = 0;
        lives = 3;
        {
            //PathGeneration
            PathGenerator mazeGenerator = new PathGenerator(rows, columns);
            boolean[][] mazeLayout = mazeGenerator.generatePath();
            Maze maze = new Maze(table);
            int counter = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    counter += 1;
                    if (mazeLayout[i][j]) {
                        if (counter % 3 != 0) {
                            cells[i][j].addObject(maze);
                            view.repaintCell(j, i);
                        }
                    }
                }
            }
            //PathGeneration
        }



        createGhost();
        createGhost();
        createGhost();



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
                        moveAllGhosts();
                        view.setLivesOnPanel(lives);
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
                if (seconds % 5 == 0) {
                    createPoint();
                }
            }
            private void createPoint() {
                int x = model.getRandomIntWithinColumns();
                int y = model.getRandomIntWithinRows();
                Collisional content = cells[y][x].getContent();
                if (content instanceof Maze) { createPoint(); }
                if (content instanceof Ghost) { createPoint(); }
                if (content instanceof Point) { createPoint(); }
                else {
                    cells[y][x].addObject(point);
                    view.repaintCell(x, y);
                }
            }
            private void moveAllGhosts() {
                for (Ghost ghost : ghosts) {
                    int currentX = ghost.getXPos();
                    int currentY = ghost.getYPos();
                    ghost.move();
                    int newX = ghost.getXPos();
                    int newY = ghost.getYPos();

                    cells[currentY][currentX].removeObject(ghost);
                    cells[newY][newX].addObject(ghost);

                    view.repaintCell(currentX, currentY);
                    view.repaintCell(newX, newY);
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

                view.repaintCell(currentX, currentY);
                view.repaintCell(currentX + dx, currentY + dy);
            }
        });
    }
    private void createGhost() {
        int x = model.getRandomIntWithinColumns();
        int y = model.getRandomIntWithinRows();
        Collisional content;
        content = cells[y][x].getContent();
        if (!(content instanceof Maze)) {
            Ghost ghost = new Ghost(x, y, table, view);
            cells[y][x].addObject(ghost);
            view.repaintCell(x, y);
            ghosts.add(ghost);
        }
    }
    public static void subtractLife() { lives -= 1; }
    public static void addScore() { score += 10; }
}
