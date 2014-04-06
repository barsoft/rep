package com.barsoft.java_labs2.lab7.client.net;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.barsoft.java_labs2.lab7.client.Cell;
import com.barsoft.java_labs2.lab7.client.SingleGamePanel;
import com.barsoft.java_labs2.lab7.client.managers.NetworkGameManager;
import com.barsoft.java_labs2.lab7.client.net.ClientManager.OnGameStateUpdatedListener;
import com.barsoft.java_labs2.lab7.entities.Game;
import com.barsoft.java_labs2.lab7.entities.Game.GameState;

public class NetworkGamePanel extends SingleGamePanel {

	private boolean switching = false;

	public NetworkGamePanel(Game game) {
		super(game);
	}

	@Override
	public void init(Game game) {
		this.gameManager = new NetworkGameManager(game);
		newGame();
	}

	@Override
	public void newGame() {
		cells = new Cell[gameManager.getGame().getMapSize()][gameManager
				.getGame().getMapSize()];

		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		for (int i = 0; i < gameManager.getGame().getMapSize(); i++) {
			for (int j = 0; j < gameManager.getGame().getMapSize(); j++) {
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.gridx = i;
				gbc_btnNewButton.gridy = j;
				gbc_btnNewButton.fill = GridBagConstraints.BOTH;
				gbc_btnNewButton.weightx = 1;
				gbc_btnNewButton.weighty = 1;
				cells[i][j] = new Cell();
				this.add(cells[i][j], gbc_btnNewButton);

				if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1) {
					cells[i][j].setBackground(Color.black);
				} else {
					cells[i][j].setBackground(Color.white);
				}

				cells[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Cell cell = (Cell) e.getSource();
						int x = ((GridBagLayout) getLayout())
								.getConstraints(cell).gridx;
						int y = ((GridBagLayout) getLayout())
								.getConstraints(cell).gridy;

						NetworkGameManager ngm = (NetworkGameManager) gameManager;

						if (ngm.isMyTurn() && !switching)
							click(x, y);
						if (switching) {
							resetSelection();
						}
					}
				});
			}
		}
		resetSelection();

		TCPClient
				.getInstance()
				.getClientManager()
				.addOnGameStateUpdatedListener(
						new OnGameStateUpdatedListener() {

							@Override
							public void updated(GameState gameState) {
								switching = false;
								gameManager.getGame().setGameState(gameState);
								resetSelection();
							}
						});
	}

	@Override
	public void switchTurn() {
		TCPClient
				.getInstance()
				.getRequestBuilder()
				.switchTurn(gameManager.getGame().getId(),
						gameManager.getGame().getGameState());
		switching = true;
	}
}
