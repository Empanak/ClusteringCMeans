package Modelos;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Component;
public class CustomTableCellRenderer extends DefaultTableCellRenderer {
    private Font contentFont;
    private Font headerFont;

    public CustomTableCellRenderer() {
        contentFont = new Font("Georgia", Font.PLAIN, 14);
        headerFont = new Font("Georgia", Font.PLAIN, 18);
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
