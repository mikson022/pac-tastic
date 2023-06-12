package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Objects;

public class MainMenuView extends JFrame {
    private final JButton newGame = new JButton("New Game");
    private final JButton highScore = new JButton("High Scores");
    private final JButton exit = new JButton("Exit");

    public MainMenuView() {
        JLabel label = new JLabel();
        this.setTitle("PACMAN");

        ImageIcon pacIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pacIcon.png")));
        this.setIconImage(pacIcon.getImage());

        ImageIcon upperGreeter = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("UpperPlaceGreeter.jpg")));
        label.setIcon(upperGreeter);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);

        Border borderOfMain = BorderFactory.createLineBorder(Color.RED, 5);
        label.setBorder(borderOfMain);

        newGame.setBounds(5, 200, 988, 200);
        highScore.setBounds(5, 400, 988, 200);
        exit.setBounds(5, 600, 988, 200);

        newGame.setFocusPainted(false);
        highScore.setFocusPainted(false);
        exit.setFocusPainted(false);

        newGame.setBackground(Color.BLACK);
        highScore.setBackground(Color.BLACK);
        exit.setBackground(Color.BLACK);

        newGame.setForeground(Color.RED);
        highScore.setForeground(Color.RED);
        exit.setForeground(Color.RED);

        newGame.setToolTipText("Starts new game, you will be asked for the size of board");
        highScore.setToolTipText("Displays high score list");
        exit.setToolTipText("Exits the game");

        newGame.setBorderPainted(false);
        highScore.setBorderPainted(false);
        exit.setBorderPainted(false);

        Font fontForButtons = new Font("IDK", Font.ITALIC, 40);

        newGame.setFont(fontForButtons);
        highScore.setFont(fontForButtons);
        exit.setFont(fontForButtons);

        this.add(label);
        this.add(newGame);
        this.add(highScore);
        this.add(exit);

        this.add(label);
        this.setSize(1000,850);
        setLocationRelativeTo(null);
        setResizable(false);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
    }
    public JButton getNewGameButton() { return newGame; }
    public JButton getHighScoreButton() { return highScore; }
    public JButton getExitButton() { return exit; }
}
