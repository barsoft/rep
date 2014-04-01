package com.barsoft.java_labs.core.forms;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import com.barsoft.java_labs.core.ConnectionInfo;
import com.barsoft.java_labs.core.ConnectionInfo.DatabaseType;

public class ConnectionJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldServer;
	private JTextField textFieldPort;
	private JComboBox comboBoxDatabaseType;

	private ConnectionInfo connection;
	private JLabel lblLogin;
	private JTextField textFieldLogin;
	private JLabel lblPassword;
	private JLabel label_1;
	private JTextField textFieldDatabaseName;
	private JPasswordField passwordFieldPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConnectionJDialog dialog = new ConnectionJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConnectionJDialog() {

		setBounds(100, 100, 377, 356);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 20, 297, 20, 0 };
		gbl_contentPanel.rowHeights = new int[] { 15, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblServer = new JLabel("Server:");
			GridBagConstraints gbc_lblServer = new GridBagConstraints();
			gbc_lblServer.insets = new Insets(0, 0, 5, 5);
			gbc_lblServer.gridx = 1;
			gbc_lblServer.gridy = 1;
			contentPanel.add(lblServer, gbc_lblServer);
		}
		{
			textFieldServer = new JTextField();
			textFieldServer.setText("localhost");
			textFieldServer.setColumns(10);
			GridBagConstraints gbc_textFieldServer = new GridBagConstraints();
			gbc_textFieldServer.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldServer.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldServer.gridx = 1;
			gbc_textFieldServer.gridy = 2;
			contentPanel.add(textFieldServer, gbc_textFieldServer);
		}
		{
			JLabel label = new JLabel("Port:");
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 1;
			gbc_label.gridy = 3;
			contentPanel.add(label, gbc_label);
		}
		{
			textFieldPort = new JTextField();
			textFieldPort.setText("3306");
			textFieldPort.setColumns(10);
			GridBagConstraints gbc_textFieldPort = new GridBagConstraints();
			gbc_textFieldPort.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldPort.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldPort.gridx = 1;
			gbc_textFieldPort.gridy = 4;
			contentPanel.add(textFieldPort, gbc_textFieldPort);
		}
		{
			JLabel lblDatabasse = new JLabel("Database Type:");
			GridBagConstraints gbc_lblDatabasse = new GridBagConstraints();
			gbc_lblDatabasse.insets = new Insets(0, 0, 5, 5);
			gbc_lblDatabasse.gridx = 1;
			gbc_lblDatabasse.gridy = 5;
			contentPanel.add(lblDatabasse, gbc_lblDatabasse);
		}
		{
			comboBoxDatabaseType = new JComboBox();
			comboBoxDatabaseType.setModel(new DefaultComboBoxModel<>(
					DatabaseType.values()));
			comboBoxDatabaseType.setSelectedIndex(0);
			GridBagConstraints gbc_comboBoxDatabaseType = new GridBagConstraints();
			gbc_comboBoxDatabaseType.insets = new Insets(0, 0, 5, 5);
			gbc_comboBoxDatabaseType.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBoxDatabaseType.gridx = 1;
			gbc_comboBoxDatabaseType.gridy = 6;
			contentPanel.add(comboBoxDatabaseType, gbc_comboBoxDatabaseType);
		}
		{
			lblLogin = new JLabel("Login:");
			GridBagConstraints gbc_lblLogin = new GridBagConstraints();
			gbc_lblLogin.insets = new Insets(0, 0, 5, 5);
			gbc_lblLogin.gridx = 1;
			gbc_lblLogin.gridy = 7;
			contentPanel.add(lblLogin, gbc_lblLogin);
		}
		{
			textFieldLogin = new JTextField();
			textFieldLogin.setText("root");
			textFieldLogin.setColumns(10);
			GridBagConstraints gbc_textFieldLogin = new GridBagConstraints();
			gbc_textFieldLogin.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldLogin.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldLogin.gridx = 1;
			gbc_textFieldLogin.gridy = 8;
			contentPanel.add(textFieldLogin, gbc_textFieldLogin);
		}
		{
			lblPassword = new JLabel("Password:");
			GridBagConstraints gbc_lblPassword = new GridBagConstraints();
			gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
			gbc_lblPassword.gridx = 1;
			gbc_lblPassword.gridy = 9;
			contentPanel.add(lblPassword, gbc_lblPassword);
		}
		{
			passwordFieldPassword = new JPasswordField();
			passwordFieldPassword.setText("root");
			GridBagConstraints gbc_passwordFieldPassword = new GridBagConstraints();
			gbc_passwordFieldPassword.insets = new Insets(0, 0, 5, 5);
			gbc_passwordFieldPassword.fill = GridBagConstraints.HORIZONTAL;
			gbc_passwordFieldPassword.gridx = 1;
			gbc_passwordFieldPassword.gridy = 10;
			contentPanel.add(passwordFieldPassword, gbc_passwordFieldPassword);
		}
		{
			label_1 = new JLabel("Database Name:");
			GridBagConstraints gbc_label_1 = new GridBagConstraints();
			gbc_label_1.insets = new Insets(0, 0, 5, 5);
			gbc_label_1.gridx = 1;
			gbc_label_1.gridy = 11;
			contentPanel.add(label_1, gbc_label_1);
		}
		{
			textFieldDatabaseName = new JTextField();
			textFieldDatabaseName.setText("db");
			textFieldDatabaseName.setColumns(10);
			GridBagConstraints gbc_textFieldDatabaseName = new GridBagConstraints();
			gbc_textFieldDatabaseName.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldDatabaseName.fill = GridBagConstraints.HORIZONTAL;
			gbc_textFieldDatabaseName.gridx = 1;
			gbc_textFieldDatabaseName.gridy = 12;
			contentPanel.add(textFieldDatabaseName, gbc_textFieldDatabaseName);
		}

	}

	public static ConnectionInfo showConnectionDialog(Component parent,
			String title) {
		ConnectionJDialog dialog = new ConnectionJDialog();
		int res = JOptionPane.showConfirmDialog(parent, dialog.getComponents(),
				title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
				null);

		if (res == JOptionPane.OK_OPTION) {
			ConnectionInfo c = new ConnectionInfo();
			c.server = dialog.textFieldServer.getText();
			c.port = dialog.textFieldPort.getText();
			c.databaseType = (DatabaseType) dialog.comboBoxDatabaseType
					.getSelectedItem();
			c.dataBaseName = dialog.textFieldDatabaseName.getText();
			if (c.databaseType == DatabaseType.derby) {
				c.driverClass = "org.apache.derby.jdbc.ClientDriver";
			} else {
				c.driverClass = "com.mysql.jdbc.Driver";
			}
			c.login = dialog.textFieldLogin.getText();
			c.password = dialog.passwordFieldPassword.getText();
			return c;
		} else if (res == JOptionPane.CANCEL_OPTION) {
		}
		return null;
	}

}
