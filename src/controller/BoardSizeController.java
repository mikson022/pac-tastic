package controller;

import view.BoardSizeView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSizeController implements ActionListener {
    private final HighScoreController highScoreController;
    private final BoardSizeView view;
    public BoardSizeController(HighScoreController highScoreController) {
        view = new BoardSizeView();
        this.highScoreController = highScoreController;
    }

    public void showPrompt() {
        view.getCancelButton().addActionListener(this);
        view.getOkButton().addActionListener(this);
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.display();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getCancelButton()) {
            view.close();
        } else if (e.getSource() == view.getOkButton()) {
            setArea();
        }
    }
    private void setArea() {
        int rows = convertStringToInt(view.getRowsInput());
        int columns = convertStringToInt(view.getColumnsInput());
        if (rows >= 10 && rows <= 100 && columns >= 10  && columns <= 100) {
            new GameController(rows, columns, highScoreController);
            view.close();
        } else {
            view.clearValues();
        }
    }
    private int convertStringToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
