package Graphics;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * A custom {@code JTable} that allows for dynamic row and column addition, and provides customized cell rendering.
 */
public class Info extends JTable
{

    /**
     * Constructs an {@code Info} table with the specified column names.
     *
     * @param columnNames A {@code Vector} of {@code String} representing the column names for the table.
     */
    public Info(Vector<String> columnNames) {
        super(new DefaultTableModel(columnNames, 0)); // 0 rows initially
        initializeTable();
    }


    /**
     * Initializes the table settings such as row height and cell renderer.
     */
    private void initializeTable() {
        // Example customization: Set a specific row height
        this.setRowHeight(30);

        // Example customization: Set a custom cell renderer
        this.setDefaultRenderer(Object.class, new CustomCellRenderer());
    }


    /**
     * Adds a new row of data to the table.
     *
     * @param rowData An array of {@code Object} representing the data for the new row.
     */
    public void addRow(Object[] rowData) {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.addRow(rowData);
    }

    /**
     * Adds a new column to the table with the specified column data.
     *
     * @param rowData The data to be used for the new column. Each item in the array represents a cell in the column.
     */
    public void addCol(Object rowData) {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.addColumn(rowData);
    }

    /**
     * Sets the table header values with the specified column names.
     *
     * @param tableHeaderValues A {@code Vector} of {@code String} representing the column names for the table header.
     */
    public void setTableHeaderValues(Vector<String> tableHeaderValues) {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.setColumnIdentifiers(tableHeaderValues);
    }


    /**
     * A custom cell renderer that alters the appearance of table cells.
     */
    private static class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (isSelected) {
                cellComponent.setBackground(Color.YELLOW); // Highlight selected cells
            } else {
                cellComponent.setBackground(Color.WHITE);
            }
            return cellComponent;
        }
    }
}
