package com.barsoft.java_labs2.lab7.entities;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.barsoft.java_labs2.lab7.entities.Checker.CheckerState;

public class Game {
	private int mapSize = 8;
	private int rowsCount = 3;
	private Player player1;
	private Player player2;
	private long id;
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
	private GameState gameState;

	public Game(Player player1, Player player2) {
		this.id = ID_GENERATOR.getAndIncrement();
		this.player1 = player1;
		this.player2 = player2;
		this.gameState = new GameState();

		for (int i = 0; i < getMapSize(); i++) {
			for (int j = 0; j < getRowsCount(); j++) {
				if (i % 2 == 0 && j % 2 == 0 || i % 2 == 1 && j % 2 == 1) {
					Checker checker = new Checker();
					checker.setX(i);
					checker.setY(j);
					checker.setCheckerState(CheckerState.White);
					getGameState().getCheckers()[i][j] = checker;
				}
			}
		}

		for (int i = 0; i < getMapSize(); i++) {
			for (int j = getMapSize() - getRowsCount(); j < getMapSize(); j++) {
				if (i % 2 == 1 && j % 2 == 1 || i % 2 == 0 && j % 2 == 0) {
					Checker checker = new Checker();
					checker.setX(i);
					checker.setY(j);
					checker.setCheckerState(CheckerState.Black);
					getGameState().getCheckers()[i][j] = checker;
				}
			}
		}
	}

	public int getMapSize() {
		return mapSize;
	}

	public void setMapSize(int mapSize) {
		this.mapSize = mapSize;
	}

	public int getRowsCount() {
		return rowsCount;
	}

	public void setRows(int rows) {
		this.rowsCount = rows;
	}

	public long getId() {
		return id;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public class GameState {
		private Checker[][] checkers;

		private CheckerState currentCheckerState = CheckerState.White;

		public GameState() {
			checkers = new Checker[getMapSize()][getMapSize()];
		}

		public Checker[][] getCheckers() {
			return checkers;
		}

		public CheckerState getCurrentCheckerState() {
			return currentCheckerState;
		}

		public void setCurrentCheckerState(CheckerState currentCheckerState) {
			this.currentCheckerState = currentCheckerState;
		}

		public void print() {
			for (int i = 0; i < gameState.getCheckers().length; i++) {
				for (int j = 0; j < gameState.getCheckers().length; j++) {
					if (gameState.getCheckers()[j][i] == null)
						System.out.print("- ");
					else if (gameState.getCheckers()[j][i].getCheckerState() == CheckerState.Black)
						System.out.print("b ");
					else if (gameState.getCheckers()[j][i].getCheckerState() == CheckerState.White)
						System.out.print("w ");
				}
				System.out.println("");
			}
		}

	}
}
