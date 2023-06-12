package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
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

        highScoresList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        highScoresList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(highScoresList);
        scrollPane.setPreferredSize(new Dimension(400, 600));

        add(scrollPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }
    public void setHighScoreList(String entry) {
        highScoresListModel.addElement(entry);
    }
}
