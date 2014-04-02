package com.barsoft.java_labs.lab2.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.barsoft.java_labs.Main;
import com.barsoft.java_labs.core.forms.ImagePanel;
import com.barsoft.java_labs.lab2.entities.User;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.CardLayout;

public class CheckersFrame extends JFrame {
	protected JMenu mnFile;

	private JTextField loginField;
	private JTextField passwordField;
	private JMenuItem mntmNewMenuItem;
	private CheckersPanel checkersPanel;
	private ImagePanel contentPane;
	private GridBagLayout gbl_contentPane;

	/**
	 * Create the frame.
	 */
	public CheckersFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 556);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem jMenuItemClose = new JMenuItem();
		jMenuItemClose.setText("Login");
		jMenuItemClose.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showLoginWindow();
			}

		});
		mnFile.add(jMenuItemClose);

		contentPane = new ImagePanel(Main.class.getResource(
				"/com/barsoft/java_labs/background.jpg").getPath());
		contentPane.setPreferredSize(new Dimension(500, 500));
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 400 };
		gbl_contentPane.rowHeights = new int[] { 400 };
		gbl_contentPane.columnWeights = new double[] { 0.0 };
		gbl_contentPane.rowWeights = new double[] { 0.0 };
		contentPane.setLayout(gbl_contentPane);

		checkersPanel = new CheckersPanel();
		GridBagConstraints gbc_checkersPanel = new GridBagConstraints();
		gbc_checkersPanel.fill = GridBagConstraints.BOTH;
		gbc_checkersPanel.gridx = 0;
		gbc_checkersPanel.gridy = 0;
		contentPane.add(checkersPanel, gbc_checkersPanel);

		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				int width = (int) (arg0.getComponent().getHeight() * 0.7);
				gbl_contentPane.columnWidths = new int[] { width };
				int height = (int) (arg0.getComponent().getHeight() * 0.7);
				gbl_contentPane.rowHeights = new int[] { height };
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				int width = (int) (arg0.getComponent().getHeight() * 0.7);
				gbl_contentPane.columnWidths = new int[] { width };
				int height = (int) (arg0.getComponent().getHeight() * 0.7);
				gbl_contentPane.rowHeights = new int[] { height };
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent arg0) {
				showLoginWindow();
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void showLoginWindow() {
		JPanel myPanel;
		myPanel = new JPanel();
		GridBagLayout gbl_myPanel = new GridBagLayout();
		gbl_myPanel.columnWidths = new int[] { 161, 0 };
		gbl_myPanel.rowHeights = new int[] { 0, 20, 0, 0, 0, 0, 0 };
		gbl_myPanel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_myPanel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		myPanel.setLayout(gbl_myPanel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 0);
		gbc.gridx = 0;
		gbc.gridy = 1;
		JLabel label_1 = new JLabel("Login:");
		myPanel.add(label_1, gbc);

		loginField = new JTextField(5);
		GridBagConstraints gbc_xField = new GridBagConstraints();
		gbc_xField.fill = GridBagConstraints.HORIZONTAL;
		gbc_xField.anchor = GridBagConstraints.NORTH;
		gbc_xField.insets = new Insets(5, 5, 5, 0);
		gbc_xField.gridx = 0;
		gbc_xField.gridy = 2;
		myPanel.add(loginField, gbc_xField);
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.anchor = GridBagConstraints.WEST;
		gbc_1.insets = new Insets(5, 5, 5, 0);
		gbc_1.gridx = 0;
		gbc_1.gridy = 3;
		JLabel label = new JLabel("Password:");
		myPanel.add(label, gbc_1);
		passwordField = new JTextField(5);
		GridBagConstraints gbc_yField = new GridBagConstraints();
		gbc_yField.insets = new Insets(5, 5, 5, 0);
		gbc_yField.fill = GridBagConstraints.HORIZONTAL;
		gbc_yField.anchor = GridBagConstraints.NORTH;
		gbc_yField.gridx = 0;
		gbc_yField.gridy = 4;
		myPanel.add(passwordField, gbc_yField);
		int result = JOptionPane.showConfirmDialog(CheckersFrame.this, myPanel,
				"Login", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null);

		if (result == JOptionPane.OK_OPTION) {

			try {
				User user = DatabaseService.getInstance().loginUser(
						loginField.getText(), passwordField.getText());
				if (user != null) {

				} else {
					throw new SQLException("Wrong input data!");
				}
			} catch (SQLException e) {
				show();
			}
		}
	}
}
