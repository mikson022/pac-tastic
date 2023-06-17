package view;

import object.Pacman;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
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
        for (int i = 0; i < table.getColumnCount(); i++) { table.getColumnModel().getColumn(i).setPreferredWidth(10); }

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
        add(table);
        pack();
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setVisible(true);
    }
    public void updateView() {
        table.repaint();
    }


}
