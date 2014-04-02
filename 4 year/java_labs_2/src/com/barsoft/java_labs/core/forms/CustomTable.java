/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.core.forms;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * @author Maks
 */
public class CustomTable extends JTable {

	@Override
	public DefaultTableCellRenderer getCellRenderer(int row, int column) {
		return new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(
					javax.swing.JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component rendererComp = super.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);

				if (!isSelected) {
					if (!table.isCellEditable(row, column)) {
						rendererComp.setBackground(new Color(230, 230, 250));
						rendererComp.setForeground(Color.BLACK);
					} else {
						rendererComp.setBackground(new Color(250, 250, 250));
						rendererComp.setForeground(Color.BLACK);
					}
				} else {
					if (!table.isCellEditable(row, column)) {
						rendererComp.setBackground(new Color(130, 130, 150));
						rendererComp.setForeground(Color.WHITE);
					} else {
						rendererComp.setBackground(new Color(150, 150, 180));
						rendererComp.setForeground(Color.WHITE);
					}
				}

				return rendererComp;
			}
		};
	}
}
