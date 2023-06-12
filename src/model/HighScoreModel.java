package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class HighScoreModel implements Serializable {
    private List<HighScoreEntry> highScoreLogs;
    public HighScoreModel() {
        highScoreLogs = new LinkedList<>();
    }
    public List<HighScoreEntry> getHighScoreLogs() {
        return highScoreLogs;
    }
    public void addHighScore(String name, int score) {
        HighScoreEntry entry = new HighScoreEntry(name, score);
        highScoreLogs.add(entry);
    }
}
class HighScoreEntry {
    private String name;
    private int score;
    HighScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }
}