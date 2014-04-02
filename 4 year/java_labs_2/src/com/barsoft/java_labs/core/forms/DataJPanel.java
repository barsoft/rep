package com.barsoft.java_labs.core.forms;

import java.awt.Component;
import java.awt.Container;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.barsoft.java_labs.lab1.DatabaseJPanel;
import com.barsoft.java_labs.lab1.entities.BankClient;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

public class DataJPanel extends JPanel {

	private CustomTable dataTable;
	private Object object;
	private ArrayList<TableModelListener> tableModelListeners = new ArrayList<>();

	/**
	 * Creates new form DataJPanel
	 */
	public DataJPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 46, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		jScrollPane1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(5, 5, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(jScrollPane1, gbc_scrollPane);

		JLabel lblSearch = new JLabel("Search:");
		GridBagConstraints gbc_lblSearch = new GridBagConstraints();
		gbc_lblSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSearch.gridwidth = 3;
		gbc_lblSearch.insets = new Insets(5, 5, 5, 5);
		gbc_lblSearch.gridx = 0;
		gbc_lblSearch.gridy = 1;
		add(lblSearch, gbc_lblSearch);

		textFieldSearch = new JTextField();
		GridBagConstraints gbc_textFieldSearch = new GridBagConstraints();
		gbc_textFieldSearch.gridwidth = 3;
		gbc_textFieldSearch.insets = new Insets(5, 5, 5, 5);
		gbc_textFieldSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldSearch.gridx = 0;
		gbc_textFieldSearch.gridy = 2;
		add(textFieldSearch, gbc_textFieldSearch);
		textFieldSearch.setColumns(10);

		jButtonAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		gbc_btnAdd.insets = new Insets(5, 5, 5, 5);
		gbc_btnAdd.gridx = 0;
		gbc_btnAdd.gridy = 3;
		add(jButtonAdd, gbc_btnAdd);

		jButtonUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.fill = GridBagConstraints.BOTH;
		gbc_btnUpdate.insets = new Insets(5, 5, 5, 5);
		gbc_btnUpdate.gridx = 1;
		gbc_btnUpdate.gridy = 3;
		add(jButtonUpdate, gbc_btnUpdate);

		jButtonDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(5, 5, 5, 5);
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.gridx = 2;
		gbc_btnDelete.gridy = 3;
		add(jButtonDelete, gbc_btnDelete);

		dataTable = new CustomTable();
		jScrollPane1.setViewportView(dataTable);
	}

	public void select(Object obj) {
		this.object = obj;
		int j = 0;
		Object[][] matrix = new Object[100][2];
		for (Method method : obj.getClass().getDeclaredMethods()) {
			if (Modifier.isPublic(method.getModifiers())
					&& method.getParameterTypes().length == 0
					&& method.getReturnType() != void.class
					&& (method.getName().startsWith("get") || method.getName()
							.startsWith("is"))) {
				Object value = null;
				try {
					value = method.invoke(obj);
				} catch (IllegalAccessException ex) {
					Logger.getLogger(DatabaseJPanel.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (IllegalArgumentException ex) {
					Logger.getLogger(DatabaseJPanel.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (InvocationTargetException ex) {
					Logger.getLogger(DatabaseJPanel.class.getName()).log(
							Level.SEVERE, null, ex);
				}

				String s = method.getName();
				StringBuilder sb = null;
				if (s.startsWith("get")) {
					sb = new StringBuilder(s);
					sb.replace(0, 3, "");
				}
				if (s.startsWith("is")) {
					sb = new StringBuilder(s);
					sb.replace(0, 2, "");
				}
				matrix[j][0] = sb.toString();
				matrix[j][1] = value;

				j++;
			}
		}
		matrix = Arrays.copyOfRange(matrix, 0, j);

		final DefaultTableModel md = new DefaultTableModel(matrix,
				new String[] { "Field", "Value" }) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if (column == 0) {
					return false;
				} else {
					return true;
				}
			}
		};

		dataTable.setModel(md);
		dataTable.setRowHeight(40);

		for (TableModelListener l : tableModelListeners) {
			md.addTableModelListener(l);
		}

	}

	public HashMap<String, Object> getFormInfo() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < dataTable.getRowCount(); i++) {
			map.put(dataTable.getValueAt(i, 0).toString(),
					dataTable.getValueAt(i, 1));
		}
		return map;
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		for (Component cmp : getComponents(this)) {
			cmp.setEnabled(enabled);
		}
	}

	private Component[] getComponents(Component container) {
		ArrayList<Component> list = null;

		try {
			list = new ArrayList<Component>(
					Arrays.asList(((Container) container).getComponents()));
			for (int index = 0; index < list.size(); index++) {
				for (Component currentComponent : getComponents(list.get(index))) {
					list.add(currentComponent);
				}
			}
		} catch (ClassCastException e) {
			list = new ArrayList<Component>();
		}

		return list.toArray(new Component[list.size()]);
	}

	private javax.swing.JScrollPane jScrollPane1;
	private JButton jButtonDelete;
	private JButton jButtonAdd;
	private JButton jButtonUpdate;
	private JTextField textFieldSearch;

	// End of variables declaration//GEN-END:variables

	public JTextField getTextFieldSearch() {
		return textFieldSearch;
	}

	public JButton getjButtonAdd() {
		return jButtonAdd;
	}

	public JButton getjButtonDelete() {
		return jButtonDelete;
	}

	public JButton getjButtonUpdate() {
		return jButtonUpdate;
	}

	public CustomTable getDataTable() {
		return dataTable;
	}

	void addTableModelListener(TableModelListener tableModelListener) {
		tableModelListeners.add(tableModelListener);
	}
}
