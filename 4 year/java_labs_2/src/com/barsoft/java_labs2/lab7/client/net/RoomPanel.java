package com.barsoft.java_labs2.lab7.client.net;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.barsoft.java_labs2.lab7.client.net.TCPClient;
import com.barsoft.java_labs2.lab7.client.net.ClientManager.OnDownloadListener;
import com.barsoft.java_labs2.lab7.entities.User;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RoomPanel extends JPanel implements Runnable {

	private JList list;
	private JLabel lblHello;
	private int userIndex;
	private JButton btnLeaveRoom;
	private boolean stop;

	public JButton getBtnLeaveRoom() {
		return btnLeaveRoom;
	}

	/**
	 * Create the frame.
	 */

	public RoomPanel(final long roomID) {
		setBounds(100, 100, 300, 300);
		setLayout(new BorderLayout(0, 0));

		lblHello = new JLabel("Hello "
				+ TCPClient.getInstance().getUser().getLogin()
				+ "! Wait for opponent! ("
				+ TCPClient.getInstance().getClientManager().getRoom(roomID)
						.toString() + ")");
		lblHello.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblHello.setForeground(Color.BLACK);
		GridBagConstraints gbc_lblHello = new GridBagConstraints();
		gbc_lblHello.anchor = GridBagConstraints.WEST;
		gbc_lblHello.insets = new Insets(0, 0, 5, 5);
		gbc_lblHello.gridx = 0;
		gbc_lblHello.gridy = 0;
		this.add(lblHello, BorderLayout.NORTH);

		list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 2;
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		this.add(list);

		btnLeaveRoom = new JButton("Leave room");
		add(btnLeaveRoom, BorderLayout.SOUTH);

		TCPClient.getInstance().getClientManager()
				.addOnRoomsDownloadListener(new OnDownloadListener() {

					@Override
					public void download() {
						DefaultListModel<User> mdl = new DefaultListModel<>();

						ListSelectionListener listSelectionListener = new ListSelectionListener() {

							private int userIndex;

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

						for (User u : TCPClient.getInstance()
								.getClientManager().getRoom(roomID).getUsers()) {
							mdl.addElement(u);
						}

						if (mdl.getSize() > 0)
							mdl.getElementAt(userIndex);

						list.addListSelectionListener(listSelectionListener);
						list.setModel(mdl);

						list.setSelectedIndex(userIndex);
					}
				});
		new Thread(this).start();
	}

	@Override
	public synchronized void run() {
		stop = false;
		while (!stop) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!stop)
				TCPClient.getInstance().getRequestBuilder().requestRooms();
		}
	}

	public void stop() {
		stop = true;
	}
}
