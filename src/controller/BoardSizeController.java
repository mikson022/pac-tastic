package controller;

import model.BoardSizeModel;
import view.BoardSizeView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSizeController implements ActionListener{
    private final BoardSizeView view;
    private final BoardSizeModel model;

    public BoardSizeController() {
        view = new BoardSizeView();
        model = new BoardSizeModel();
    }

    public void showPrompt() {
        view.getCancelButton().addActionListener(this);
        view.getOkButton().addActionListener(this);
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        view.display();
    }
    private void fetchArea() {
        String getRow = view.getRowsInput();
        String getColumn = view.getColumnsInput();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getCancelButton()) {
            view.close();
        } else if (e.getSource() == view.getOkButton()) {
            fetchArea();
        }
    }
}
