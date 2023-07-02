package model;

import java.util.*;

public class MazeGenerator {
    private final int rows;
    private final int columns;
    private final boolean[][] maze;
    private final Random random;

    public MazeGenerator(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.maze = new boolean[rows][columns];
        this.random = new Random();
    }

    public boolean[][] generateMaze() {
        initializeMaze();

        int startX = 0; 
        int startY = 0;

        generatePath(startX, startY);

        return maze;
    }

    private void initializeMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = true;
            }
        }
    }

    private void generatePath(int x, int y) {
        maze[x][y] = false;  // Open the cell

        int[][] directions = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
        shuffleArray(directions);

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (isValidCell(newX, newY) && maze[newX][newY]) {
                int wallX = x + direction[0] / 2;
                int wallY = y + direction[1] / 2;
                maze[wallX][wallY] = false;  // Open the wall
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
        return x >= 0 && x < rows && y >= 0 && y < columns;
    }
}
