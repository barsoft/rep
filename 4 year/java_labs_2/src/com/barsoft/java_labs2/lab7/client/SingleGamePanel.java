package com.barsoft.java_labs2.lab7.client;


import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;

import org.apache.derby.impl.sql.execute.AvgAggregator;
import org.apache.derby.impl.store.access.PC_XenaVersion;

import com.barsoft.java_labs2.lab7.client.Cell;
import com.barsoft.java_labs2.lab7.client.managers.SingleGameManager;
import com.barsoft.java_labs2.lab7.client.net.TCPClient;
import com.barsoft.java_labs2.lab7.client.net.ClientManager.OnGameStateUpdatedListener;
import com.barsoft.java_labs2.lab7.entities.Checker;
import com.barsoft.java_labs2.lab7.entities.Player;
import com.barsoft.java_labs2.lab7.entities.Checker.CheckerState;
import com.barsoft.java_labs2.lab7.entities.Game.GameState;
import com.barsoft.java_labs2.lab7.entities.Game;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;

public class SingleGamePanel extends JPanel implements Runnable {

	protected Cell[][] cells;
	protected SingleGameManager gameManager;
	protected Point pickedCell;

	public SingleGamePanel(Game game) {
		init(game);
	}

	public void init(Game game) {
		this.gameManager = new SingleGameManager(game);
		newGame();
	}

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
						click(x, y);
					}
				});
			}
		}
		resetSelection();
	}

	public void repaintCells() {
		for (int i = 0; i < gameManager.getGame().getMapSize(); i++) {
			for (int j = 0; j < gameManager.getGame().getMapSize(); j++) {
				cells[i][j].setChecker(gameManager.getGame().getGameState()
						.getCheckers()[i][j]);
			}
		}
		this.revalidate();
		this.repaint();
	}

	public void resetSelection() {
		for (int i = 0; i < gameManager.getGame().getMapSize(); i++) {
			for (int j = 0; j < gameManager.getGame().getMapSize(); j++) {
				cells[i][j].setAvailable(false);
				cells[i][j].setOnKick(false);
				cells[i][j].setPicked(false);
				pickedCell = null;
			}
		}
		repaintCells();
	}

	public void click(int x, int y) {

		if (!gameManager.isCellEmpty(x, y)) {
			if (gameManager.isCurrentCheckerState(x, y)) {
				pickedCell = pick(x, y);
			}
		} else {
			boolean clickedCellIsAvailable = pickedCell != null
					&& gameManager.isCellAvailable(pickedCell.x, pickedCell.y,
							x, y);
			if (clickedCellIsAvailable) {
				boolean kicked = gameManager.moveChecker(pickedCell.x,
						pickedCell.y, x, y);
				gameManager.getGame().getGameState().print();
				if (!kicked) {
					switchTurn();
				} else {
					if (gameManager.getOnKickCells(x, y).size() != 0) {
						pickedCell = pickForDouble(x, y);
					} else {
						switchTurn();
					}
				}

			} else {
				resetSelection();
			}
		}
	}

	public Point pick(int x, int y) {
		resetSelection();
		cells[x][y].setPicked(true);
		for (Point availableCell : gameManager.getAvailvbleCells(x, y)) {
			cells[availableCell.x][availableCell.y].setAvailable(true);
		}
		for (Point kickCell : gameManager.getOnKickCells(x, y)) {
			cells[kickCell.x][kickCell.y].setOnKick(true);
		}
		return new Point(x, y);
	}

	public Point pickForDouble(int x, int y) {
		resetSelection();
		cells[x][y].setPicked(true);
		for (Point availableCell : gameManager.getOnDoubleKickAvailableCells(x,
				y)) {
			cells[availableCell.x][availableCell.y].setAvailable(true);
		}
		for (Point kickCell : gameManager.getOnKickCells(x, y)) {
			cells[kickCell.x][kickCell.y].setOnKick(true);
		}
		return new Point(x, y);
	}

	public void switchTurn() {
		if (gameManager.getGame().getGameState().getCurrentCheckerState() == CheckerState.Black)
			gameManager.getGame().getGameState()
					.setCurrentCheckerState(CheckerState.White);
		else {
			gameManager.getGame().getGameState()
					.setCurrentCheckerState(CheckerState.Black);
		}
		resetSelection();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public void stop() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		Player player = new Player();
		player.setCheckerState(CheckerState.White);
		Player oppponentPlayer = new Player();
		oppponentPlayer.setCheckerState(CheckerState.Black);
		jFrame.add(new SingleGamePanel(new Game(player, oppponentPlayer)));
		jFrame.setSize(500, 500);
		jFrame.setVisible(true);
	}
}
