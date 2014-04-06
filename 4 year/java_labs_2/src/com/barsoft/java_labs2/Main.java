package com.barsoft.java_labs2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.UIManager;

import com.barsoft.java_labs2.core.forms.ImagePanel;
import com.barsoft.java_labs2.lab6.DatabaseFrame;
import com.barsoft.java_labs2.lab7.client.CheckersFrame;
import com.barsoft.java_labs2.lab7.client.net.TCPClient;
import com.barsoft.java_labs2.lab7.server.TCPServer;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main extends JFrame {

	private JPanel contentPane;

	private static Main main;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				main = new Main();
				main.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 663, 493);

		contentPane = new ImagePanel(Main.class.getResource("background.jpg"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0 };
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints g1 = new GridBagConstraints();
		g1.gridx = 0;
		g1.gridy = 1;
		g1.gridwidth = 3;

		JPanel jp = new JPanel();
		jp.setPreferredSize(new Dimension(100, 20));
		final JTextArea jt = new JTextArea();
		jp.add(jt);
		// jt.setText("ip           ");

		contentPane.add(jp, g1);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Leisure");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);

		JButton buttonLeisure = new JButton("");
		buttonLeisure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.setVisible(false);
				// final CheckersFrame f = new CheckersFrame() {
				// {
				// JMenuItem jMenuItemClose = new JMenuItem();
				// jMenuItemClose.setText("Close");
				// jMenuItemClose
				// .addActionListener(new java.awt.event.ActionListener() {
				// public void actionPerformed(
				// java.awt.event.ActionEvent evt) {
				// setVisible(false);
				// main.setBounds(getX(), getY(),
				// getWidth(), getHeight());
				// main.setVisible(true);
				// }
				// });
				// mnFile.add(jMenuItemClose);
				// }
				// };
				// f.setVisible(true);
				// f.setBounds(getX(), getY(), getWidth(), getHeight());
				try {
					//TCPClient.main(new String[] { jt.getText() });
					TCPClient.main(new String[] { jt.getText() });
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonLeisure.setIcon(new ImageIcon(Main.class
				.getResource("shashki_logo.jpg")));
		panel.add(buttonLeisure, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		contentPane.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblBusiness = new JLabel("Business");
		lblBusiness.setForeground(Color.BLACK);
		lblBusiness.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblBusiness, BorderLayout.SOUTH);

		JButton buttonBuisness = new JButton("");
		buttonBuisness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				final DatabaseFrame f = new DatabaseFrame() {
					{
						JMenuItem jMenuItemClose = new JMenuItem();
						jMenuItemClose.setText("Close");
						jMenuItemClose
								.addActionListener(new java.awt.event.ActionListener() {
									public void actionPerformed(
											java.awt.event.ActionEvent evt) {
										setVisible(false);
										main.setBounds(getX(), getY(),
												getWidth(), getHeight());
										main.setVisible(true);
									}
								});
						jMenuFile.add(jMenuItemClose);
					}
				};
				f.setVisible(true);
				f.setBounds(getX(), getY(), getWidth(), getHeight());
			}
		});
		buttonBuisness.setIcon(new ImageIcon(Main.class
				.getResource("buisness.jpg")));
		panel_1.add(buttonBuisness, BorderLayout.NORTH);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.gridx = 2;
		gbc_panel_2.gridy = 0;
		contentPane.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblServer = new JLabel("Server");
		lblServer.setForeground(Color.BLACK);
		lblServer.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblServer, BorderLayout.SOUTH);

		JButton buttonServer = new JButton("");
		buttonServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.setVisible(false);
				// final f = new DatabaseFrame() {
				// {
				// JMenuItem jMenuItemClose = new JMenuItem();
				// jMenuItemClose.setText("Close");
				// jMenuItemClose
				// .addActionListener(new java.awt.event.ActionListener() {
				// public void actionPerformed(
				// java.awt.event.ActionEvent evt) {
				// setVisible(false);
				// main.setBounds(getX(), getY(),
				// getWidth(), getHeight());
				// main.setVisible(true);
				// }
				// });
				// jMenuFile.add(jMenuItemClose);
				// }
				// };
				// f.setVisible(true);
				// f.setBounds(getX(), getY(), getWidth(), getHeight());
				try {
					TCPServer.main(null);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buttonServer
				.setIcon(new ImageIcon(Main.class.getResource("server.jpg")));
		panel_2.add(buttonServer, BorderLayout.NORTH);
		
		 try {
			System.out.println("Your Host addr: " + InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}

}
