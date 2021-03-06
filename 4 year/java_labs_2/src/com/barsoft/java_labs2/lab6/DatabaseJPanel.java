package com.barsoft.java_labs2.lab6;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.barsoft.java_labs2.core.forms.CustomTable;
import com.barsoft.java_labs2.lab6.database_connectors.Lab1Connector;
import com.barsoft.java_labs2.lab6.entities.BankClient;
import com.barsoft.java_labs2.lab6.entities.Currency;
import com.barsoft.java_labs2.lab6.entities.DepositType;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DatabaseJPanel extends JPanel {

	/**
	 * Creates new form Lab1JPanel
	 */
	private ArrayList<CustomTable> tables = new ArrayList<>();
	private CustomTable jTableBankClients = new CustomTable();
	private CustomTable jTableCurrencies = new CustomTable();
	private CustomTable jTableDepositTypes = new CustomTable();
	private Lab1Connector db;

	public DatabaseJPanel(final Lab1Connector db) throws SQLException {
		initComponents();
		this.db = db;

		this.setBackground(Color.LIGHT_GRAY);

		jTabbedPane.setTitleAt(0, "Bank clients");
		jTabbedPane.setTitleAt(1, "Currencies");
		jTabbedPane.setTitleAt(2, "Deposit types");

		jTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (tables.get(jTabbedPane.getSelectedIndex()).getRowCount() > 0) {
					tables.get(jTabbedPane.getSelectedIndex())
							.setRowSelectionInterval(0, 0);
					dataPanel.select(getCurrentObjectFromCurrentTable());
					dataPanel.getjButtonUpdate().setEnabled(true);
					dataPanel.getjButtonDelete().setEnabled(true);
				} else {
					dataPanel.select(createCurrentTableSample());
					dataPanel.getjButtonUpdate().setEnabled(false);
					dataPanel.getjButtonDelete().setEnabled(false);
				}
			}
		});

		jScrollPane1.setViewportView(jTableBankClients);
		tables.add(jTableBankClients);
		jScrollPane2.setViewportView(jTableCurrencies);
		tables.add(jTableCurrencies);
		jScrollPane3.setViewportView(jTableDepositTypes);
		tables.add(jTableDepositTypes);

		dataPanel.getjButtonAdd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dataPanel.getDataTable().isEditing()) {
					dataPanel.getDataTable().getCellEditor().stopCellEditing();
				}
				try {
					switch (jTabbedPane.getSelectedIndex()) {
					case 0: {
						DatabaseJPanel.this.db
								.addBankCleint((BankClient) parseObjectFromDataTable());
						break;
					}
					case 1: {
						DatabaseJPanel.this.db
								.addCurrency((Currency) parseObjectFromDataTable());
						break;
					}
					case 2: {
						DatabaseJPanel.this.db
								.addDepositType((DepositType) parseObjectFromDataTable());
						break;
					}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Wrong input data!");
					return;
				}
				updateTables();

				if (tables.get(jTabbedPane.getSelectedIndex()).getRowCount() > 0) {
					int y = tables.get(jTabbedPane.getSelectedIndex())
							.getRowCount() - 1;
					tables.get(jTabbedPane.getSelectedIndex())
							.setRowSelectionInterval(y, y);
					dataPanel.select(getCurrentObjectFromCurrentTable());
				}
			}
		});

		dataPanel.getjButtonDelete().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dataPanel.getDataTable().isEditing()) {
					dataPanel.getDataTable().getCellEditor().stopCellEditing();
				}
				try {
					switch (jTabbedPane.getSelectedIndex()) {
					case 0: {
						BankClient obj = (BankClient) getCurrentObjectFromCurrentTable();
						DatabaseJPanel.this.db.deleteBankCleint(obj.getId());
						break;
					}
					case 1: {
						Currency obj = (Currency) getCurrentObjectFromCurrentTable();
						DatabaseJPanel.this.db.deleteCurrency(obj.getId());
						break;
					}
					case 2: {
						DepositType obj = (DepositType) getCurrentObjectFromCurrentTable();
						DatabaseJPanel.this.db.deleteDepositType(obj.getId());
						break;
					}
					}
				} catch (SQLIntegrityConstraintViolationException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Object has references in other tables!");
					return;
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Wrong input data!");
					return;
				}

				int y = tables.get(jTabbedPane.getSelectedIndex())
						.getSelectedRow();

				updateTables();

				JTable t = tables.get(jTabbedPane.getSelectedIndex());

				if (t.getRowCount() > 0) {
					if (y >= t.getRowCount()) {
						t.setRowSelectionInterval(t.getRowCount() - 1,
								t.getRowCount() - 1);
					} else {
						t.setRowSelectionInterval(y, y);
					}
					dataPanel.select(getCurrentObjectFromCurrentTable());
				}
			}
		});

		dataPanel.getjButtonUpdate().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dataPanel.getDataTable().isEditing()) {
					dataPanel.getDataTable().getCellEditor().stopCellEditing();
				}

				try {
					switch (jTabbedPane.getSelectedIndex()) {
					case 0: {
						BankClient obj = (BankClient) getCurrentObjectFromCurrentTable();
						DatabaseJPanel.this.db.updateBankCleint(obj.getId(),
								(BankClient) parseObjectFromDataTable());
						break;
					}
					case 1: {
						Currency obj = (Currency) getCurrentObjectFromCurrentTable();
						DatabaseJPanel.this.db.updateCurrency(obj.getId(),
								(Currency) parseObjectFromDataTable());
						break;
					}
					case 2: {
						DepositType obj = (DepositType) getCurrentObjectFromCurrentTable();
						DatabaseJPanel.this.db.updateDepositType(obj.getId(),
								(DepositType) parseObjectFromDataTable());
						break;
					}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Wrong input data!");
					return;
				}

				int y = tables.get(jTabbedPane.getSelectedIndex())
						.getSelectedRow();

				updateTables();

				if (tables.get(jTabbedPane.getSelectedIndex()).getRowCount() > 0) {
					tables.get(jTabbedPane.getSelectedIndex())
							.setRowSelectionInterval(y, y);
					dataPanel.select(getCurrentObjectFromCurrentTable());
				}
			}
		});

		updateTables();

		dataPanel.getDataTable().setDefaultEditor(Object.class,
				new CustomCellEditor());// .getDataTable().setCellEditor(

		dataPanel.getTextFieldSearch().getDocument()
				.addDocumentListener(new DocumentListener() {

					@Override
					public void removeUpdate(DocumentEvent arg0) {
						warn();
					}

					@Override
					public void insertUpdate(DocumentEvent arg0) {
						warn();
					}

					@Override
					public void changedUpdate(DocumentEvent arg0) {
						warn();
					}

					public void warn() {
						db.setFilter(dataPanel.getTextFieldSearch().getText());
						updateCurrentTable();
						if (tables.get(jTabbedPane.getSelectedIndex())
								.getRowCount() > 0) {
							tables.get(jTabbedPane.getSelectedIndex())
									.setRowSelectionInterval(0, 0);
							dataPanel
									.select(getCurrentObjectFromCurrentTable());
						}
					}
				});

		jTabbedPane.setSelectedIndex(1);
		jTabbedPane.setSelectedIndex(0);
	}

	public void updateTables() {
		try {
			final TableModel modelBankClients = new CustomTableModel(
					getMatrixFromList(db.getBankClients()),
					getColumnsList(BankClient.class));
			jTableBankClients.setModel(modelBankClients);
			final TableModel modelCurrencies = new CustomTableModel(
					getMatrixFromList(db.getCurrencies()),
					getColumnsList(Currency.class));
			jTableCurrencies.setModel(modelCurrencies);
			final TableModel modelDepositTypes = new CustomTableModel(
					getMatrixFromList(db.getDepositTypes()),
					getColumnsList(DepositType.class));
			jTableDepositTypes.setModel(modelDepositTypes);

			for (final JTable jTable : tables) {
				jTable.getSelectionModel().addListSelectionListener(
						new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								if (e.getValueIsAdjusting()) {
									return;
								}
								if (jTable.getSelectedRow() >= 0) {
									dataPanel
											.select(getCurrentObjectFromCurrentTable());
								}
							}
						});
				jTable.getSelectionModel().setSelectionMode(
						ListSelectionModel.SINGLE_SELECTION);
			}

			if (tables.get(jTabbedPane.getSelectedIndex()).getRowCount() > 0) {
				dataPanel.getjButtonUpdate().setEnabled(true);
				dataPanel.getjButtonDelete().setEnabled(true);
			} else {
				dataPanel.getjButtonUpdate().setEnabled(false);
				dataPanel.getjButtonDelete().setEnabled(false);
			}

		} catch (SQLException ex) {
			Logger.getLogger(DatabaseJPanel.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public void updateCurrentTable() {
		try {
			switch (jTabbedPane.getSelectedIndex()) {
			case 0: {
				final TableModel modelBankClients = new CustomTableModel(
						getMatrixFromList(db.getBankClients()),
						getColumnsList(BankClient.class));
				jTableBankClients.setModel(modelBankClients);
				break;
			}
			case 1: {
				final TableModel modelCurrencies = new CustomTableModel(
						getMatrixFromList(db.getCurrencies()),
						getColumnsList(Currency.class));
				jTableCurrencies.setModel(modelCurrencies);
				break;
			}
			case 2: {
				final TableModel modelDepositTypes = new CustomTableModel(
						getMatrixFromList(db.getDepositTypes()),
						getColumnsList(DepositType.class));
				jTableDepositTypes.setModel(modelDepositTypes);
				break;
			}
			}

			final JTable jTable = tables.get(jTabbedPane.getSelectedIndex());

			jTable.getSelectionModel().addListSelectionListener(
					new ListSelectionListener() {
						public void valueChanged(ListSelectionEvent e) {
							if (e.getValueIsAdjusting()) {
								return;
							}
							if (jTable.getSelectedRow() >= 0) {
								dataPanel
										.select(getCurrentObjectFromCurrentTable());
							}
						}
					});
			jTable.getSelectionModel().setSelectionMode(
					ListSelectionModel.SINGLE_SELECTION);

			if (tables.get(jTabbedPane.getSelectedIndex()).getRowCount() > 0) {
				dataPanel.getjButtonUpdate().setEnabled(true);
				dataPanel.getjButtonDelete().setEnabled(true);
			} else {
				dataPanel.getjButtonUpdate().setEnabled(false);
				dataPanel.getjButtonDelete().setEnabled(false);
			}

		} catch (SQLException ex) {
			Logger.getLogger(DatabaseJPanel.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	private Object parseObjectFromDataTable() {
		int tableIndex = jTabbedPane.getSelectedIndex();
		if (tableIndex == 0) {
			BankClient obj = new BankClient();

			try {
				obj.setName(dataPanel.getFormInfo().get("Name").toString());
				Currency c = (Currency) dataPanel.getFormInfo().get("Currency");
				DepositType d = (DepositType) dataPanel.getFormInfo().get(
						"DepositType");
				obj.setCurrency(c);
				obj.setDepositType(d);
				obj.setAmount(Integer.parseInt(dataPanel.getFormInfo()
						.get("Amount").toString()));
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Wrong input data!");
				return null;
			}
			return obj;
		} else if (tableIndex == 1) {
			Currency obj = new Currency();

			try {
				obj.setName(dataPanel.getFormInfo().get("Name").toString());
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Wrong input data!");
				return null;
			}
			return obj;
		} else if (tableIndex == 2) {
			DepositType obj = new DepositType();

			try {
				obj.setName(dataPanel.getFormInfo().get("Name").toString());
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Wrong input data!");
				return null;
			}
			return obj;
		}
		return null;
	}

	private Object getCurrentObjectFromCurrentTable() {
		int tableIndex = jTabbedPane.getSelectedIndex();
		int index = tables.get(tableIndex).getSelectedRow();
		try {
			switch (tableIndex) {
			case 0:
				return db.getBankClients().get(index);
			case 1:
				return db.getCurrencies().get(index);
			case 2:
				return db.getDepositTypes().get(index);
			}
		} catch (SQLException ex) {
			Logger.getLogger(DatabaseJPanel.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}

	private Object createCurrentTableSample() {
		switch (jTabbedPane.getSelectedIndex()) {
		case 0:
			return new BankClient();
		case 1:
			return new Currency();
		case 2:
			return new DepositType();
		}
		return null;
	}

	private void resetSelections() {
		for (JTable jTable : tables) {
			jTable.clearSelection();
		}
	}

	private Object[] getColumnsList(Class obj) {
		ArrayList<String> columnNames = new ArrayList<>();
		for (Method method : obj.getDeclaredMethods()) {

			if (Modifier.isPublic(method.getModifiers())
					&& method.getParameterTypes().length == 0
					&& method.getReturnType() != void.class
					&& (method.getName().startsWith("get") || method.getName()
							.startsWith("is"))) {
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
				columnNames.add(sb.toString());
			}
		}
		return columnNames.toArray();
	}

	private Object[][] getMatrixFromList(List<? extends Object> list) {
		int j = 0;
		int i = 0;
		Object[][] matrix = new Object[list.size()][100];
		for (Object obj : list) {
			j = 0;
			for (Method method : obj.getClass().getDeclaredMethods()) {

				if (Modifier.isPublic(method.getModifiers())
						&& method.getParameterTypes().length == 0
						&& method.getReturnType() != void.class
						&& (method.getName().startsWith("get") || method
								.getName().startsWith("is"))) {
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

					matrix[i][j] = value;
					j++;
				}
			}
			i++;
		}

		return matrix;
	}

	public static class CustomTableModel extends DefaultTableModel {

		public CustomTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	public class CustomCellEditor extends DefaultCellEditor {

		private DefaultCellEditor other = new DefaultCellEditor(
				new JTextField());
		private DefaultCellEditor comboBoxBankClients;
		private DefaultCellEditor comboBoxCurrencies;
		private DefaultCellEditor comboBoxDepositTypes;
		private DefaultCellEditor lastSelected;

		public CustomCellEditor() {
			super(new JTextField());

		}

		@Override
		public Object getCellEditorValue() {

			return lastSelected.getCellEditorValue();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			try {
				this.comboBoxBankClients = new DefaultCellEditor(new JComboBox(
						db.getBankClients().toArray()));
				this.comboBoxCurrencies = new DefaultCellEditor(new JComboBox(
						db.getCurrencies().toArray()));
				this.comboBoxDepositTypes = new DefaultCellEditor(
						new JComboBox(db.getDepositTypes().toArray()));
			} catch (SQLException ex) {
				Logger.getLogger(DatabaseJPanel.class.getName()).log(
						Level.SEVERE, null, ex);
				return null;
			}
			if (column == 1) {
				if (table.getValueAt(row, 0).toString().equals("BankClient")) {
					lastSelected = comboBoxBankClients;
					return comboBoxBankClients.getTableCellEditorComponent(
							table, value, isSelected, row, column);
				} else if (table.getValueAt(row, 0).toString()
						.equals("Currency")) {
					lastSelected = comboBoxCurrencies;
					return comboBoxCurrencies.getTableCellEditorComponent(
							table, value, isSelected, row, column);
				} else if (table.getValueAt(row, 0).toString()
						.equals("DepositType")) {
					lastSelected = comboBoxDepositTypes;
					return comboBoxDepositTypes.getTableCellEditorComponent(
							table, value, isSelected, row, column);
				}
			}
			lastSelected = other;
			return other.getTableCellEditorComponent(table, value, isSelected,
					row, column);
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jTabbedPane = new javax.swing.JTabbedPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane2 = new javax.swing.JScrollPane();
		jScrollPane3 = new javax.swing.JScrollPane();
		dataPanel = new com.barsoft.java_labs2.core.forms.DataJPanel();

		setBorder(javax.swing.BorderFactory.createEtchedBorder());
		setEnabled(false);

		jTabbedPane.addTab("tab3", jScrollPane1);
		jTabbedPane.addTab("tab3", jScrollPane2);
		jTabbedPane.addTab("tab3", jScrollPane3);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jTabbedPane,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										383, Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(dataPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										250,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTabbedPane,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.DEFAULT_SIZE, 464,
						Short.MAX_VALUE)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(dataPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										0, Short.MAX_VALUE).addContainerGap()));
	}// </editor-fold>//GEN-END:initComponents
		// Variables declaration - do not modify//GEN-BEGIN:variables

	private com.barsoft.java_labs2.core.forms.DataJPanel dataPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JTabbedPane jTabbedPane;

}
