/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Owner
 */
public class WarnaTableResepFarmasi extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        component.setForeground(new Color(50,50,50));
        // Set alternating row colors
        if (row % 2 == 1) {
            component.setBackground(new Color(255, 244, 244));
        } else {
            component.setBackground(new Color(255, 255, 255));
        }

        // Color duplicates in column 4 blue
        Object currentValue = table.getValueAt(row, 4);
        for (int i = 0; i < table.getRowCount(); i++) {
            if (i != row && currentValue.equals(table.getValueAt(i, 4))) {
                component.setBackground(new Color(200,0,0));
                component.setForeground(new Color(255,230,230));
                break;
            }
        }
        
        if(table.getValueAt(row,7).toString().equals("Sudah Terlayani")){
            component.setBackground(new Color(67, 126, 59));
            component.setForeground(new Color(255,230,230));
        }

        return component;
    }
}
