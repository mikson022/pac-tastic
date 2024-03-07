package view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class HighScoreView extends JFrame{
    private DefaultListModel<String> highScoresListModel;
    private JList<String> highScoresList;
    public HighScoreView() {
        highScoresListModel = new DefaultListModel<>();
        highScoresList = new JList<>();
        highScoresList.setModel(highScoresListModel);

        this.setTitle("High Score");

        ImageIcon pacIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pics/pacIcon.png")));
        this.setIconImage(pacIcon.getImage());
        setLayout(new BorderLayout());

        highScoresList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 18));
        highScoresList.setForeground(Color.RED);
        highScoresList.setBackground(Color.BLACK);
        highScoresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(highScoresList);
        scrollPane.setPreferredSize(new Dimension(500, 700));

        add(scrollPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }
    public void addHighScoreLogToViewList(String entry) {
        highScoresListModel.addElement(entry);
    }
    public void clearViewList() {
        highScoresListModel.clear();
    }
}
