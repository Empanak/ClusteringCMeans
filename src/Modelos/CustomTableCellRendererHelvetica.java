package Modelos;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRendererHelvetica extends DefaultTableCellRenderer {
    private final Font contentFont;
    private final Font headerFont;

    public CustomTableCellRendererHelvetica() {
        contentFont = new Font("Helvetica", Font.BOLD, 14);
        headerFont = new Font("Helvetica", Font.BOLD, 14);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (row >= 0) {
            setFont(contentFont);
        } else {
            setFont(headerFont);
            //setUI(new javax.swing.plaf.basic.BasicTableHeaderUI());
        }
        return component;
    }

}
