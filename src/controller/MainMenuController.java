package controller;

import view.MainMenuView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {
    private final MainMenuView view;
    private final HighScoreController highScoreController;
    public MainMenuController() {
        view = new MainMenuView();
        highScoreController = new HighScoreController();
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.getNewGameButton().addActionListener(this);
        view.getHighScoreButton().addActionListener(this);
        view.getExitButton().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getNewGameButton()) {
            view.getNewGameButton();
        } else if (e.getSource() == view.getHighScoreButton()) {
            highScoreController.presentHighScoreTable();
        } else if (e.getSource() == view.getExitButton()) {
            System.exit(0);
        }
    }
}


