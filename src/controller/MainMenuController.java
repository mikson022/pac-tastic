package controller;

import view.MainMenuView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {
    private final MainMenuView view;
    private final BoardSizeController boardSizeController;
    private final GameController gameController;
    public MainMenuController() {
        view = new MainMenuView();
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.getNewGameButton().addActionListener(this);
        view.getHighScoreButton().addActionListener(this);
        view.getExitButton().addActionListener(this);

        boardSizeController = new BoardSizeController();
        gameController = new GameController();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getNewGameButton()) {
            boardSizeController.showPrompt();
        } else if (e.getSource() == view.getHighScoreButton()) {
            view.getHighScoreButton();
        } else if (e.getSource() == view.getExitButton()) {
            System.exit(0);
        }
    }
}


