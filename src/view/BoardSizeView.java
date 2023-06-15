package view;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BoardSizeView {
    private final JFrame frame;
    private final JTextField rowsTextField;
    private final JTextField columnsTextField;
    private final JButton okButton;
    private final JButton cancelButton;

    public BoardSizeView() {
        frame = new JFrame(" Available: 10-100 ");
        frame.setSize(320, 115);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        ImageIcon pacIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/pics/pacIcon.png")));
        frame.setIconImage(pacIcon.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel rowsLabel = new JLabel("Number of Rows:");
        rowsTextField = new JTextField(10);

        JLabel columnsLabel = new JLabel("Number of Columns:");
        columnsTextField = new JTextField(10);

        okButton = new JButton("OK");
        okButton.setFocusable(false);

        cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        panel.add(rowsLabel);
        panel.add(rowsTextField);
        panel.add(columnsLabel);
        panel.add(columnsTextField);
        panel.add(okButton);
        panel.add(cancelButton);

        frame.setLocationRelativeTo(null);
        frame.add(panel, BorderLayout.CENTER);
    }

    public JButton getOkButton() { return okButton; }
    public JButton getCancelButton() { return cancelButton; }
    public String getRowsInput() { return rowsTextField.getText(); }
    public String getColumnsInput() { return columnsTextField.getText(); }
    public void clearValues() { rowsTextField.setText(""); columnsTextField.setText("");}
    public void display() { frame.setVisible(true); }
    public void close() { clearValues(); frame.dispose(); }
    public void setDefaultCloseOperation(int e) { clearValues(); }
}
