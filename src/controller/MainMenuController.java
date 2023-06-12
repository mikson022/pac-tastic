package controller;

import view.MainMenuView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {
    private final MainMenuView view;
    public MainMenuController() {
        view = new MainMenuView();
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
            view.getHighScoreButton();
        } else if (e.getSource() == view.getExitButton()) {
            System.exit(0);
        }
    }
}


