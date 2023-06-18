package view;

import object.Pacman;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Objects;

public class GameView extends JFrame{
    private final JTable table;
    private final Pacman pacman;
    public GameView(Pacman pacman, JTable table) {
        this.pacman = pacman;
        this.table = table;
        initializeUI();
    }
    private void initializeUI() {
        setTitle("PACMAN");

        ImageIcon pacIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("pics/pacIcon.png")));
        setIconImage(pacIcon.getImage());

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

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scaleProperly();
            }
        });

        add(table);

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new GridLayout(2, 4, 10, 10 ));
        panelSouth.setPreferredSize(new Dimension(100, 100));
        Border bRed = BorderFactory.createLineBorder(Color.RED, 5);
        panelSouth.setBorder(bRed);
        panelSouth.setBackground(Color.BLACK);


        JLabel timeLabel = new JLabel("Time");
        panelSouth.add(timeLabel);
        JLabel livesLabel = new JLabel("Lives");
        panelSouth.add(livesLabel);
        JLabel scoreLabel = new JLabel("Score");
        panelSouth.add(scoreLabel);
        JLabel levLabel = new JLabel("Level");
        panelSouth.add(levLabel);
        // temporary
        JLabel timeL = new JLabel("00:00");
        panelSouth.add(timeL);
        JLabel livesL = new JLabel("3");
        panelSouth.add(livesL);
        JLabel scoreL = new JLabel("0");
        panelSouth.add(scoreL);
        JLabel levL = new JLabel("1");
        panelSouth.add(levL);

        add(panelSouth, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setVisible(true);
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
    public void updateView() {
        table.repaint();
    }


}