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
import com.barsoft.java_labs2.lab7.entities.Room;
import com.barsoft.java_labs2.lab7.entities.User;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RoomsListPanel extends JPanel implements Runnable {

	private JList list;
	private JLabel lblHello;
	private JButton btnGo;
	private Room selectedRoom;
	private int roomIndex;
	private boolean stop;

	public RoomsListPanel() {
		setBounds(100, 100, 300, 300);
		setLayout(new BorderLayout(0, 0));

		lblHello = new JLabel("Hello "
				+ TCPClient.getInstance().getUser().getLogin()
				+ "! Select room:");
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

		btnGo = new JButton("GO!");

		add(btnGo, BorderLayout.SOUTH);

		TCPClient.getInstance().getClientManager()
				.addOnRoomsDownloadListener(new OnDownloadListener() {

					@Override
					public void download() {
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

						for (Room r : TCPClient.getInstance()
								.getClientManager().getRooms()) {
							mdl.addElement(r);
						}

						if (mdl.getSize() > 0)
							selectedRoom = mdl.getElementAt(roomIndex);

						list.addListSelectionListener(listSelectionListener);
						list.setModel(mdl);

						list.setSelectedIndex(roomIndex);
					}

				});
		new Thread(this).start();
	}

	@Override
	public void run() {
		stop = false;
		while (!stop) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			TCPClient.getInstance().getRequestBuilder().requestRooms();
		}
	}

	public void stop() {
		stop = true;
	}

	public Room getSelectedRoom() {
		return selectedRoom;
	}

	public JButton getBtnGo() {
		return btnGo;
	}
}
