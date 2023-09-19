package controller;

import model.HSEntry;
import model.HighScoreModel;
import view.HighScoreView;

import java.io.IOException;

public class HighScoreController {
    private final HighScoreModel model;
    private final HighScoreView view;

    public HighScoreController() {
        model = new HighScoreModel();
        view = new HighScoreView();
    }
    // Will be used to add entry after game is over
    public void addHSEntry(String name, int score) {
        // Sorting done right after addition
        model.addHighScoreLog(name, score);
    }
    public void presentHighScoreTable() {
        view.clearViewList();
        model.deserializeHSLogs();
        passDataToHSView();
        view.setVisible(true);
    }
    private void passDataToHSView() {
        for (int i = 0; i < model.getHighScoreLogs().size(); i++) {
            HSEntry entry = model.getHighScoreLogs().get(i);
            String entryStr = entry.getNameAndScore();
            view.addHighScoreLogToViewList(entryStr);
        }
    }
}
