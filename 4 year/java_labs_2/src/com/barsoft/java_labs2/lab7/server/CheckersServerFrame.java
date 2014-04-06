package com.barsoft.java_labs2.lab7.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.DefaultCaret;
import javax.swing.JTextArea;

import com.barsoft.java_labs2.core.ConnectionInfo;
import com.barsoft.java_labs2.core.ConnectionInfo.DatabaseType;
import com.barsoft.java_labs2.core.forms.ConnectionJDialog;
import com.barsoft.java_labs2.lab7.entities.Room;
import com.barsoft.java_labs2.lab7.entities.User;
import com.barsoft.java_labs2.lab7.server.database_connectors.DatabaseConnector;

import javax.swing.JMenuItem;
import javax.swing.JList;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

public class CheckersServerFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;

	private javax.swing.JMenuBar jMenuBar1;
	protected javax.swing.JMenu jMenuFile;
	private javax.swing.JMenuItem jMenuItemNew;
	private javax.swing.JMenuItem jMenuItemConnect;
	private DatabaseJPanel databaseJPanel;

	private JMenuItem mntmDisconnect;
	private JList roomsList;
	private JList usersOnlineList;
	private JPanel panel;
	private JLabel lblDatabase;
	private JLabel lblOnlineUsers;
	private JLabel lblRooms;

	/**
	 * Create the frame.
	 */
	public CheckersServerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		textArea = new JTextArea();
		textArea.setRows(10);
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.BOLD, 15));

		JScrollPane jsp = new JScrollPane(textArea);
		getContentPane().add(jsp, BorderLayout.SOUTH);

		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

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

		ConnectionInfo ci = new ConnectionInfo();
		ci.dataBaseName = "db4";
		ci.databaseType = DatabaseType.mysql;
		ci.login = "root";
		ci.password = "root";
		ci.port = "3306";
		ci.server = "localhost";
		ci.driverClass = "com.mysql.jdbc.Driver";

		DatabaseConnector db = DatabaseConnector.createConnector(ci);

		try {

			panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[] { 104, 0, 1, 0 };
			gbl_panel.rowHeights = new int[] { 0, 74, 0 };
			gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0,
					Double.MIN_VALUE };
			gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
			panel.setLayout(gbl_panel);

			lblDatabase = new JLabel("Database");
			GridBagConstraints gbc_lblDatabase = new GridBagConstraints();
			gbc_lblDatabase.insets = new Insets(0, 0, 5, 5);
			gbc_lblDatabase.gridx = 0;
			gbc_lblDatabase.gridy = 0;
			panel.add(lblDatabase, gbc_lblDatabase);

			lblOnlineUsers = new JLabel("Online users");
			GridBagConstraints gbc_lblOnlineUsers = new GridBagConstraints();
			gbc_lblOnlineUsers.insets = new Insets(0, 0, 5, 5);
			gbc_lblOnlineUsers.gridx = 1;
			gbc_lblOnlineUsers.gridy = 0;
			panel.add(lblOnlineUsers, gbc_lblOnlineUsers);

			lblRooms = new JLabel("Rooms");
			GridBagConstraints gbc_lblRooms = new GridBagConstraints();
			gbc_lblRooms.insets = new Insets(0, 0, 5, 0);
			gbc_lblRooms.gridx = 2;
			gbc_lblRooms.gridy = 0;
			panel.add(lblRooms, gbc_lblRooms);
			databaseJPanel = new DatabaseJPanel(db);
			GridBagConstraints gbc_databaseJPanel = new GridBagConstraints();
			gbc_databaseJPanel.fill = GridBagConstraints.BOTH;
			gbc_databaseJPanel.anchor = GridBagConstraints.NORTHWEST;
			gbc_databaseJPanel.insets = new Insets(0, 0, 0, 5);
			gbc_databaseJPanel.gridx = 0;
			gbc_databaseJPanel.gridy = 1;
			panel.add(databaseJPanel, gbc_databaseJPanel);
			databaseJPanel.updateTables();

			usersOnlineList = new JList();
			GridBagConstraints gbc_usersOnlineList = new GridBagConstraints();
			gbc_usersOnlineList.fill = GridBagConstraints.BOTH;
			gbc_usersOnlineList.anchor = GridBagConstraints.WEST;
			gbc_usersOnlineList.insets = new Insets(0, 0, 0, 5);
			gbc_usersOnlineList.gridx = 1;
			gbc_usersOnlineList.gridy = 1;
			panel.add(usersOnlineList, gbc_usersOnlineList);

			new Thread(new Runnable() {

				private Object selectedUser;
				private int userIndex;

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						DefaultListModel<User> mdl = new DefaultListModel<>();

						ListSelectionListener listSelectionListener = new ListSelectionListener() {

							public void valueChanged(
									ListSelectionEvent listSelectionEvent) {
								boolean adjust = listSelectionEvent
										.getValueIsAdjusting();
								if (!adjust) {
									JList list = (JList) listSelectionEvent
											.getSource();
									int selections[] = list
											.getSelectedIndices();
									Object selectionValues[] = list
											.getSelectedValues();
									if (selections.length > 0)
										userIndex = selections[0];
								}
							}
						};

						for (User u : TCPServer.getInstance()
								.getServerManager().getOnlineUsers()) {
							mdl.addElement(u);
						}

						if (mdl.getSize() > 0)
							selectedUser = mdl.getElementAt(userIndex);

						usersOnlineList
								.addListSelectionListener(listSelectionListener);
						usersOnlineList.setModel(mdl);

						usersOnlineList.setSelectedIndex(userIndex);
					}
				}
			}).start();

			roomsList = new JList();
			GridBagConstraints gbc_roomsList = new GridBagConstraints();
			gbc_roomsList.fill = GridBagConstraints.BOTH;
			gbc_roomsList.anchor = GridBagConstraints.WEST;
			gbc_roomsList.gridx = 2;
			gbc_roomsList.gridy = 1;
			panel.add(roomsList, gbc_roomsList);

			new Thread(new Runnable() {

				private Object selectedRoom;
				private int roomIndex;

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						DefaultListModel<Room> mdl = new DefaultListModel<>();

						ListSelectionListener listSelectionListener = new ListSelectionListener() {

							public void valueChanged(
									ListSelectionEvent listSelectionEvent) {
								boolean adjust = listSelectionEvent
										.getValueIsAdjusting();
								if (!adjust) {
									JList list = (JList) listSelectionEvent
											.getSource();
									int selections[] = list
											.getSelectedIndices();
									Object selectionValues[] = list
											.getSelectedValues();
									if (selections.length > 0)
										roomIndex = selections[0];
								}
							}
						};

						for (Room r : TCPServer.getInstance()
								.getServerManager().getRooms()) {
							mdl.addElement(r);
						}

						if (mdl.getSize() > 0)
							selectedRoom = mdl.getElementAt(roomIndex);

						roomsList
								.addListSelectionListener(listSelectionListener);
						roomsList.setModel(mdl);

						roomsList.setSelectedIndex(roomIndex);
					}
				}
			}).start();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.revalidate();
	}

	private void jMenuItemNewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemNewActionPerformed
		ConnectionInfo c = ConnectionJDialog.showConnectionDialog(this,
				"Create database");
		if (c == null)
			return;
		DatabaseConnector db = DatabaseConnector.createConnector(c);

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

		TCPServer.getInstance().logServer(
				"Database " + c.databaseType + " - " + c.dataBaseName
						+ "created!..");
	}

	private void jMenuItemConnectActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemOpenActionPerformed
		ConnectionInfo c = ConnectionJDialog.showConnectionDialog(this,
				"Connect to database");
		if (c == null)
			return;
		DatabaseConnector db = DatabaseConnector.createConnector(c);

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

		TCPServer.getInstance().logServer(
				"Database " + c.databaseType + " - " + c.dataBaseName
						+ " connected!..");
	}

	private void jMenuItemDisconnectActionPerformed(ActionEvent evt) {
		if (databaseJPanel != null) {
			this.remove(databaseJPanel);
		}
		databaseJPanel = null;
		this.revalidate();
		this.repaint();

		TCPServer.getInstance().logServer("Database disconnected!..");
	}

	public DatabaseJPanel getDatabaseJPanel() {
		return databaseJPanel;
	}

	private void exception(SQLException e) {
		// log(e.getLocalizedMessage());
		TCPServer.getInstance().logServer(e.getLocalizedMessage());
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
