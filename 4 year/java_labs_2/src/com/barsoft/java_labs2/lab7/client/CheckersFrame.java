package com.barsoft.java_labs2.lab7.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.barsoft.java_labs2.Main;
import com.barsoft.java_labs2.core.forms.ImagePanel;
import com.barsoft.java_labs2.lab7.client.net.NetworkGamePanel;
import com.barsoft.java_labs2.lab7.client.net.RoomPanel;
import com.barsoft.java_labs2.lab7.client.net.RoomsListPanel;
import com.barsoft.java_labs2.lab7.client.net.TCPClient;
import com.barsoft.java_labs2.lab7.client.net.ClientManager.OnGameStartedListener;
import com.barsoft.java_labs2.lab7.client.net.ClientManager.OnLoggedInListener;
import com.barsoft.java_labs2.lab7.entities.Game;
import com.barsoft.java_labs2.lab7.entities.User;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JList;

import java.awt.Font;

public class CheckersFrame extends JFrame {
	protected JMenu mnFile;

	private JTextField loginField;
	private JTextField passwordField;
	private ImagePanel contentPane;
	private GridBagLayout gbl_contentPane;
	private JMenuBar menuBarNonAuthorised;
	private JMenuItem jMenuItemLogin;
	private JMenuItem jMenuItemLogout;
	private RoomsListPanel roomsListPanel;
	private RoomPanel roomPanel;
	private NetworkGamePanel gamePanel;

	private String quickPass;

	private String quickLogin;

	/**
	 * Create the frame.
	 */
	public CheckersFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 891, 556);

		contentPane = new ImagePanel(Main.class.getResource("background.jpg"));
		contentPane.setPreferredSize(new Dimension(500, 500));
		contentPane.setBackground(new Color(0, 102, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();

		contentPane.setLayout(gbl_contentPane);

		menuBarNonAuthorised = new JMenuBar();
		mnFile = new JMenu("File");
		menuBarNonAuthorised.add(mnFile);

		jMenuItemLogin = new JMenuItem();
		jMenuItemLogin.setText("Login");
		jMenuItemLogin.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showLoginWindow();
			}

		});
		mnFile.add(jMenuItemLogin);

		jMenuItemLogout = new JMenuItem();
		jMenuItemLogout.setText("Logout");
		jMenuItemLogout.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TCPClient
						.getInstance()
						.getRequestBuilder()
						.logoutUser(
								TCPClient.getInstance().getUser().getLogin());
				clearAll();

				showLoginWindow();
			}

		});
		mnFile.add(jMenuItemLogout);

		setJMenuBar(menuBarNonAuthorised);

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

		if (jMenuItemLogout != null)
			jMenuItemLogout.setVisible(false);
		if (jMenuItemLogin != null)
			jMenuItemLogin.setVisible(true);

		TCPClient.getInstance().setUser(null);

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
		loginField.setText(getQuickLogin());
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
		passwordField.setText(getQuickPass());
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

			TCPClient.getInstance().getRequestBuilder()
					.loginUser(loginField.getText(), passwordField.getText());

			TCPClient.getInstance().getClientManager()
					.addOnLoggedInListener(new OnLoggedInListener() {

						@Override
						public void loggedIn(User user) {
							if (user != null) {
								if (jMenuItemLogout != null)
									jMenuItemLogout.setVisible(true);
								if (jMenuItemLogin != null)
									jMenuItemLogin.setVisible(false);

								TCPClient.getInstance().setUser(user);
								showRoomsListPanel();
							}

						}
					});
		}

		TCPClient.getInstance().getClientManager()
				.addOnGameStartedListener(new OnGameStartedListener() {

					@Override
					public void started(Game game) {
						TCPClient.getInstance().getCheckersFrame()
								.showGamePanel(game);
					}
				});
	}

	public void showRoomsListPanel() {
		clearAll();
		roomsListPanel = new RoomsListPanel();
		roomsListPanel.getBtnGo().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TCPClient
						.getInstance()
						.getRequestBuilder()
						.connectToRoom(
								roomsListPanel.getSelectedRoom().getId(),
								TCPClient.getInstance().getUser().getId());
				showRoomPanel(roomsListPanel.getSelectedRoom().getId());
			}
		});

		roomsListPanel.setPreferredSize(new Dimension(500, 250));

		contentPane.add(roomsListPanel);
		this.revalidate();
		this.repaint();
	}

	public void showRoomPanel(long roomID) {
		clearAll();
		roomPanel = new RoomPanel(roomID);
		roomPanel.getBtnLeaveRoom().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TCPClient
						.getInstance()
						.getRequestBuilder()
						.disconnectFromRoom(
								roomsListPanel.getSelectedRoom().getId(),
								TCPClient.getInstance().getUser().getId());
				showRoomsListPanel();
			}
		});
		roomPanel.setPreferredSize(new Dimension(500, 250));
		contentPane.add(roomPanel);
		this.revalidate();
		this.repaint();
	}

	public void showGamePanel(Game game) {
		clearAll();
		gamePanel = new NetworkGamePanel(game);

		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		contentPane.add(gamePanel, gridBagConstraints);

		if (game.getPlayer1().getUser().getId() == TCPClient.getInstance()
				.getUser().getId()) {
			System.out.println(game.getPlayer1().getUser() + " "
					+ TCPClient.getInstance().getUser());

			PlayerPanel playerPanel1 = new PlayerPanel();
			playerPanel1.setPlayer(game.getPlayer1());
			playerPanel1.setGame(game);
			playerPanel1.setPreferredSize(new Dimension(150, 200));
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			contentPane.add(playerPanel1, gridBagConstraints);

			PlayerPanel playerPanel2 = new PlayerPanel();
			playerPanel2.setPlayer(game.getPlayer2());
			playerPanel2.setGame(game);
			playerPanel2.setPreferredSize(new Dimension(150, 200));
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			contentPane.add(playerPanel2, gridBagConstraints);
		} else {
			PlayerPanel playerPanel1 = new PlayerPanel();
			playerPanel1.setPlayer(game.getPlayer2());
			playerPanel1.setGame(game);
			playerPanel1.setPreferredSize(new Dimension(150, 200));
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			contentPane.add(playerPanel1, gridBagConstraints);

			PlayerPanel playerPanel2 = new PlayerPanel();
			playerPanel2.setPlayer(game.getPlayer1());
			playerPanel2.setGame(game);
			playerPanel2.setPreferredSize(new Dimension(150, 200));
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			contentPane.add(playerPanel2, gridBagConstraints);
		}

		this.revalidate();
		this.repaint();
	}

	private void clearAll() {
		if (roomPanel != null) {
			roomPanel.stop();
			contentPane.remove(roomPanel);
		}
		if (roomsListPanel != null) {
			roomsListPanel.stop();
			contentPane.remove(roomsListPanel);
		}
		if (gamePanel != null) {
			gamePanel.stop();
			contentPane.remove(gamePanel);
		}
		this.revalidate();
		this.repaint();
	}

	public String getQuickPass() {
		return quickPass;
	}

	public void setQuickPass(String quickPass) {
		this.quickPass = quickPass;
	}

	public String getQuickLogin() {
		return quickLogin;
	}

	public void setQuickLogin(String quickLogin) {
		this.quickLogin = quickLogin;
	}
}
