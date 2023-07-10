package model;

import java.util.Random;

public class PathGenerator {
    private final int rows;
    private final int columns;
    private final boolean[][] wallOrNot;
    private final Random random;
    public PathGenerator(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.wallOrNot = new boolean[rows][columns];
        this.random = new Random();
    }
    public boolean[][] generatePath() {
        initializePath();
        int startX = random.nextInt(rows);
        int startY = random.nextInt(columns);
        generatePath(startX, startY);
        return wallOrNot;
    }

    private void initializePath() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                wallOrNot[i][j] = false;
            }
        }
    }

    private void generatePath(int x, int y) {
        wallOrNot[x][y] = true;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        shuffleArray(directions);
        for (int[] direction : directions) {
            int newX = x + direction[0] * 2;
            int newY = y + direction[1] * 2;
            if (isValidCell(newX, newY)) {
                int wallX = x + direction[0];
                int wallY = y + direction[1];
                wallOrNot[wallX][wallY] = true;
                generatePath(newX, newY);
            }
        }
    }

    private void shuffleArray(int[][] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int[] temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < columns && !wallOrNot[x][y];
    }
}
