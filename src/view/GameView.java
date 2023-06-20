package view;

import object.Pacman;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

public class GameView {
    private final JTable table;
    private final Pacman pacman;
    private final JFrame frame;
    private String timeOnPanel;
    private JLabel timeL = new JLabel();
    public GameView(JFrame frame, Pacman pacman, JTable table) {
        this.pacman = pacman;
        this.table = table;
        this.frame = frame;
        initializeUI();
    }
    private void initializeUI() {
        frame.setTitle("PACMAN");

        ImageIcon pacIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pics/pacIcon.png")));
        frame.setIconImage(pacIcon.getImage());

        table.setBackground(Color.BLACK);
        table.setGridColor(table.getBackground());
        for (int i = 0; i < table.getColumnCount(); i++) { table.getColumnModel().getColumn(i).setPreferredWidth(20); }

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                if (row == pacman.getYPos() && column == pacman.getXPos()) {
                    return pacman;
                } else {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            }
        });

        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scaleProperly();
            }
        });

        frame.add(table);

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new GridLayout(2, 4, 10, 10 ));
        panelSouth.setPreferredSize(new Dimension(100, 100));
        Border bRed = BorderFactory.createLineBorder(Color.RED, 5);
        panelSouth.setBorder(bRed);
        panelSouth.setBackground(Color.BLACK);

        Font labelFont = new Font("Arial", Font.BOLD, 40);
        Color labelForeground = Color.WHITE;

        JLabel timeLabel = new JLabel("Time");
        timeLabel.setFont(labelFont);
        timeLabel.setForeground(labelForeground);
        panelSouth.add(timeLabel);

        JLabel livesLabel = new JLabel("Lives");
        livesLabel.setFont(labelFont);
        livesLabel.setForeground(labelForeground);
        panelSouth.add(livesLabel);

        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setFont(labelFont);
        scoreLabel.setForeground(labelForeground);
        panelSouth.add(scoreLabel);

        JLabel levLabel = new JLabel("Level");
        levLabel.setFont(labelFont);
        levLabel.setForeground(labelForeground);
        panelSouth.add(levLabel);

        timeL.setFont(labelFont);
        timeL.setForeground(labelForeground);
        panelSouth.add(timeL);

        // below, to be updated
        JLabel livesL = new JLabel("3");
        livesL.setFont(labelFont);
        livesL.setForeground(labelForeground);
        panelSouth.add(livesL);

        JLabel scoreL = new JLabel("0");
        scoreL.setFont(labelFont);
        scoreL.setForeground(labelForeground);
        panelSouth.add(scoreL);

        JLabel levL = new JLabel("1");
        levL.setFont(labelFont);
        levL.setForeground(labelForeground);
        panelSouth.add(levL);

        frame.add(panelSouth, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
    }
    private void scaleProperly() {
        int width = table.getWidth();
        int height = table.getHeight();
        int cellSize = Math.min(width / table.getColumnCount(), height / table.getRowCount());

        table.setRowHeight(cellSize);
        for (int i = 0; i < table.getColumnCount(); i++) { table.getColumnModel().getColumn(i).setPreferredWidth(cellSize); }

        int x = pacman.getXPos() * cellSize;
        int y = pacman.getYPos() * cellSize;
        pacman.setBounds(x, y, cellSize, cellSize);
    }
    public void setTime(String time) {
        this.timeL.setText(time);
    }

}
