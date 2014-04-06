package com.barsoft.java_labs2.lab7.client;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.barsoft.java_labs2.Main;
import com.barsoft.java_labs2.core.forms.ImagePanel;
import com.barsoft.java_labs2.lab7.client.net.TCPClient;
import com.barsoft.java_labs2.lab7.client.net.ClientManager.OnGameStateUpdatedListener;
import com.barsoft.java_labs2.lab7.entities.Game;
import com.barsoft.java_labs2.lab7.entities.Player;
import com.barsoft.java_labs2.lab7.entities.Checker.CheckerState;
import com.barsoft.java_labs2.lab7.entities.Game.GameState;

import java.awt.Font;

public class PlayerPanel extends JPanel {

	private Game game;
	private Player player;
	private JLabel lblPlayername;
	private ImagePanel panel;
	private JLabel lblTurn;

	/**
	 * Create the panel.
	 */
	public PlayerPanel() {
		setLayout(new BorderLayout(0, 0));

		lblPlayername = new JLabel("playerName");
		lblPlayername.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPlayername.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPlayername, BorderLayout.NORTH);

		lblTurn = new JLabel("turn");
		lblTurn.setFont(new Font("Arial", Font.PLAIN, 18));
		add(lblTurn, BorderLayout.SOUTH);
		lblTurn.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void setPlayer(Player player) {
		this.player = player;
		lblPlayername.setText(player.getUser().getLogin());
		if (player.getCheckerState() == CheckerState.Black) {
			panel = new ImagePanel(Main.class.getResource("mud_black.png"));
		} else {
			panel = new ImagePanel(Main.class.getResource("mud_white.png"));
		}
		add(panel, BorderLayout.CENTER);
	}

	public void setGame(Game game) {
		this.game = game;
		TCPClient
				.getInstance()
				.getClientManager()
				.addOnGameStateUpdatedListener(
						new OnGameStateUpdatedListener() {

							@Override
							public void updated(GameState gameState) {
								if (gameState.getCurrentCheckerState() == player
										.getCheckerState()) {
									lblTurn.setText("Your turn!");
									lblTurn.setBackground(Color.green);
								} else {
									lblTurn.setText("Wait!");
									lblTurn.setBackground(Color.red);
								}
							}
						});
	}

}
