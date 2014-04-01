package com.barsoft.java_labs.lab2.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import com.barsoft.java_labs.core.ConnectionInfo;
import com.barsoft.java_labs.core.forms.ConnectionJDialog;
import com.barsoft.java_labs.lab2.server.database_connectors.Lab2Connector;

import javax.swing.JMenuItem;

public class CheckersServerFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	private javax.swing.JMenuBar jMenuBar1;
	protected javax.swing.JMenu jMenuFile;
	private javax.swing.JMenuItem jMenuItemNew;
	private javax.swing.JMenuItem jMenuItemConnect;
	private DatabaseJPanel databaseJPanel;

	private JMenuItem mntmDisconnect;

	/**
	 * Create the frame.
	 */
	public CheckersServerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		textArea = new JTextArea();
		textArea.setRows(10);
		textArea.setEditable(false);
		getContentPane().add(textArea, BorderLayout.SOUTH);

		jMenuFile = new javax.swing.JMenu();
		jMenuItemNew = new javax.swing.JMenuItem();
		jMenuItemConnect = new javax.swing.JMenuItem();

		jMenuBar1 = new javax.swing.JMenuBar();

		jMenuFile.setText("File");

		jMenuItemNew.setText("New DB");
		jMenuItemNew.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemNewActionPerformed(evt);
			}
		});
		jMenuFile.add(jMenuItemNew);

		jMenuItemConnect.setText("Connect DB");
		jMenuItemConnect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemConnectActionPerformed(evt);
			}
		});
		jMenuFile.add(jMenuItemConnect);

		jMenuBar1.add(jMenuFile);

		mntmDisconnect = new JMenuItem("Disconnect DB");
		mntmDisconnect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemDisconnectActionPerformed(evt);
			}

		});
		jMenuFile.add(mntmDisconnect);

		setJMenuBar(jMenuBar1);

		jMenuBar1 = new javax.swing.JMenuBar();
	}

	private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemNewActionPerformed
		ConnectionInfo c = ConnectionJDialog.showConnectionDialog(this,
				"Create database");
		if (c == null)
			return;
		Lab2Connector db = Lab2Connector.createConnector(c);

		try {
			if (!db.createDatabase())
				return;

			if (databaseJPanel != null) {
				this.remove(databaseJPanel);
			}
			databaseJPanel = new DatabaseJPanel(db);
			getContentPane().add(databaseJPanel, BorderLayout.CENTER);
			this.revalidate();
		} catch (SQLException ex) {
			exception(ex);
			return;
		}

		TCPServer.logServer("Database " + c.databaseType + " - "
				+ c.dataBaseName + "created!..");
	}

	private void jMenuItemConnectActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemOpenActionPerformed
		ConnectionInfo c = ConnectionJDialog.showConnectionDialog(this,
				"Connect to database");
		if (c == null)
			return;
		Lab2Connector db = Lab2Connector.createConnector(c);

		try {
			if (databaseJPanel != null) {
				this.remove(databaseJPanel);
			}
			databaseJPanel = new DatabaseJPanel(db);
			databaseJPanel.updateTables();
			getContentPane().add(databaseJPanel, BorderLayout.CENTER);
			this.revalidate();
		} catch (SQLException e) {
			exception(e);
			return;
		}

		TCPServer.logServer("Database " + c.databaseType + " - "
				+ c.dataBaseName + " connected!..");
	}

	private void jMenuItemDisconnectActionPerformed(ActionEvent evt) {
		if (databaseJPanel != null) {
			this.remove(databaseJPanel);
		}
		databaseJPanel = null;
		this.revalidate();
		this.repaint();

		TCPServer.logServer("Database disconnected!..");
	}

	public DatabaseJPanel getDatabaseJPanel() {
		return databaseJPanel;
	}

	private void exception(SQLException e) {
		// log(e.getLocalizedMessage());
		TCPServer.logServer(e.getLocalizedMessage());
		e.printStackTrace();
	}

	public void log(String msg) {
		System.out.println(msg);
		if (!textArea.getText().isEmpty()) {
			textArea.setText(textArea.getText() + "\n" + msg);
		} else {
			textArea.setText(msg);
		}
	}
}
