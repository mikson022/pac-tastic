package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HighScoreModel implements Serializable {
    private List<HSEntry> highScoreLogs;
    public HighScoreModel() {
        highScoreLogs = new LinkedList<>();
    }
    public List<HSEntry> getHighScoreLogs() {
        return highScoreLogs;
    }
    public void addHighScoreLog(String name, int score) {
        HSEntry entry = new HSEntry(name, score);
        highScoreLogs.add(entry);
        sortHighScoreLogs();
    }
    void sortHighScoreLogs() {
        int n = highScoreLogs.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (highScoreLogs.get(j).getScore() < highScoreLogs.get(j + 1).getScore()) {
                    Collections.swap(highScoreLogs, j, j + 1);
                }
            }
        }
    }
}
