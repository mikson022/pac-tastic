package controller;

import model.HighScoreModel;
import view.HighScoreView;

public class HighScoreController {
    private final HighScoreModel model;
    private final HighScoreView view;
    public HighScoreController() {
        model = new HighScoreModel();
        view = new HighScoreView();
    }
    public void presentHighScoreTable() {
        updateAndSortHSLogs();
        populateHSLogs();
        view.setVisible(true);
    }
    private void populateHSLogs() {
        convertHSEntriesIntoStrings();

    }
    private void updateAndSortHSLogs() {

    }
    private void convertHSEntriesIntoStrings() {
        model.getHighScoreLogs();

    }

}
