package com.barsoft.java_labs2.lab7.client.managers;

import java.awt.Point;
import java.util.ArrayList;

import org.apache.derby.impl.store.access.PC_XenaVersion;

import com.barsoft.java_labs2.lab7.client.net.TCPClient;
import com.barsoft.java_labs2.lab7.entities.Game;
import com.barsoft.java_labs2.lab7.entities.Game.GameState;
import com.barsoft.java_labs2.lab7.entities.Player;

public class NetworkGameManager extends SingleGameManager {

	public NetworkGameManager(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	public Player getMyPlayer() {
		if (game.getPlayer1().getUser().getId() == TCPClient.getInstance()
				.getUser().getId())
			return game.getPlayer1();
		else
			return game.getPlayer2();
	}

	public boolean isMyTurn() {
		return getMyPlayer().getCheckerState() == game.getGameState()
				.getCurrentCheckerState();
	}

}
