package com.barsoft.java_labs2.lab7.client.managers;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;

import org.apache.derby.impl.store.access.PC_XenaVersion;
import org.apache.derby.tools.sysinfo;

import com.barsoft.java_labs2.lab7.client.net.TCPClient;
import com.barsoft.java_labs2.lab7.entities.Game;
import com.barsoft.java_labs2.lab7.entities.Game.GameState;
import com.barsoft.java_labs2.lab7.entities.Player;

public class SingleGameManager {
	protected Game game;

	public SingleGameManager(Game game) {
		this.game = game;
		
	}

	public Game getGame() {
		return game;
	}

	public ArrayList<Point> getAvailvbleCells(int x, int y) {
		ArrayList<Point> availableCells = new ArrayList<>();
		GameState gs = game.getGameState();
		for (int i = 0; i < game.getMapSize(); i++) {
			for (int j = 0; j < game.getMapSize(); j++) {
				if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1) {
					if (Math.abs(x - i) < 2 && Math.abs(y - j) < 2) {
						if (gs.getCheckers()[i][j] != null) {
							int newi = x - (x - i) * 2;
							int newj = y - (y - j) * 2;
							if (newi < 0 || newj < 0
									|| newi >= game.getMapSize()
									|| newj >= game.getMapSize())
								continue;
							if (gs.getCheckers()[newi][newj] == null
									&& gs.getCheckers()[i][j].getCheckerState() != getCurrentPlayer()
											.getCheckerState()) {
								availableCells.add(new Point(newi, newj));
							}
						} else {
							availableCells.add(new Point(i, j));
						}
					}
				}
			}
		}
		return availableCells;
	}

	public ArrayList<Point> getOnKickCells(int x, int y) {
		ArrayList<Point> onKickCells = new ArrayList<>();
		GameState gs = game.getGameState();
		for (int i = 0; i < game.getMapSize(); i++) {
			for (int j = 0; j < game.getMapSize(); j++) {
				if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1) {
					if (Math.abs(x - i) < 2 && Math.abs(y - j) < 2) {
						if (gs.getCheckers()[i][j] != null
								&& gs.getCheckers()[i][j].getCheckerState() != getCurrentPlayer()
										.getCheckerState()) {
							int newi = x - (x - i) * 2;
							int newj = y - (y - j) * 2;
							if (newi < 0 || newj < 0
									|| newi >= game.getMapSize()
									|| newj >= game.getMapSize())
								continue;
							if (gs.getCheckers()[newi][newj] == null) {
								onKickCells.add(new Point(i, j));
							}
						}
					}
				}
			}
		}
		return onKickCells;
	}

	public ArrayList<Point> getOnDoubleKickAvailableCells(int x, int y) {
		ArrayList<Point> availableCells = getAvailvbleCells(x, y);
		for (int i = 0; i < availableCells.size(); i++) {
			Point point = availableCells.get(i);
			if (Math.abs(x - point.x) == 1 && Math.abs(y - point.y) == 1) {
				availableCells.remove(point);
			}
		}
		return availableCells;
	}

	private Player getCurrentPlayer() {
		GameState gs = game.getGameState();
		if (gs.getCurrentCheckerState() == game.getPlayer1().getCheckerState())
			return game.getPlayer1();
		if (gs.getCurrentCheckerState() == game.getPlayer2().getCheckerState())
			return game.getPlayer2();
		return null;
	}

	public boolean isCellAvailable(int pickedX, int pickedY, int x, int y) {
		ArrayList<Point> availvbleCells = getAvailvbleCells(pickedX, pickedY);
		for (Point p : availvbleCells) {
			if (p.x == x && p.y == y)
				return true;
		}
		return false;
	}

	private boolean kickCheckers(int curX, int curY, int newX, int newY) {
		boolean kicked = false;
		int kickx = (curX + newX) / 2;
		int kicky = (curY + newY) / 2;
		for (Point p : getOnKickCells(curX, curY)) {
			if (p.x == kickx && p.y == kicky) {
				game.getGameState().getCheckers()[kickx][kicky] = null;
				kicked = true;
			}
		}
		return kicked;
	}

	public boolean moveChecker(int curX, int curY, int newX, int newY) {
		GameState gs = game.getGameState();
		boolean kicked = kickCheckers(curX, curY, newX, newY);
		gs.getCheckers()[newX][newY] = gs.getCheckers()[curX][curY];
		gs.getCheckers()[curX][curY] = null;
		gs.print();
		return kicked;
	}

	public boolean isCellEmpty(int x, int y) {
		GameState gs = game.getGameState();
		return gs.getCheckers()[x][y] == null;
	}

	public boolean isCurrentCheckerState(int x, int y) {
		GameState gs = game.getGameState();
		return getCurrentPlayer().getCheckerState() == gs.getCheckers()[x][y]
				.getCheckerState();
	}
}
