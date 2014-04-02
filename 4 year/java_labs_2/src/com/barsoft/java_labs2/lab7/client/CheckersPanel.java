package com.barsoft.java_labs2.lab7.client;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class CheckersPanel extends JPanel {

	private Cell[][] cells = new Cell[8][8];

	public CheckersPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		// gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		// gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		// /gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0,
		// Double.MIN_VALUE};
		// gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		// Double.MIN_VALUE};
		setLayout(gridBagLayout);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.gridx = i;
				gbc_btnNewButton.gridy = j;
				gbc_btnNewButton.fill = GridBagConstraints.BOTH;
				gbc_btnNewButton.weightx=1;
				gbc_btnNewButton.weighty=1;
				cells[i][j] = new Cell();
				this.add(cells[i][j], gbc_btnNewButton);
			}
		}
	}
}
