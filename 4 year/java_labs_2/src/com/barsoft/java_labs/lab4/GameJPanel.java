/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab4;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Maks
 */
public class GameJPanel extends javax.swing.JPanel {

    private Game game;

    public Game getGame() {
        return game;
    }

    public GameJPanel() {
        initComponents();
    }

    public void setGame(final Game game) {
        this.removeAll();

        this.game = game;

        for (int i = 0; i < game.getWidth(); i++) {
            for (int j = 0; j < game.getHeight(); j++) {
                final TicTacToeCell cell = new TicTacToeCell();
                cell.setPreferredSize(new Dimension(64, 64));
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.anchor = GridBagConstraints.CENTER;
                constraints.gridx = i;
                constraints.gridy = j;
                cell.setGameCharacter(Game.GameCharacter.Empty);
                cell.setCellX(i);
                cell.setCellY(j);
                cell.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cellActionPerfomed(cell);
                    }
                });
                this.add(cell, constraints);
            }
        }


        loadCellsFromHistory();
    }

    private void cellActionPerfomed(TicTacToeCell cell) {
        if (!game.isFinished()) {
            cell.setGameCharacter(game.getCurrentCharacter());

            game.setCurrentX(cell.getCellX());
            game.setCurrentY(cell.getCellY());

            game.stepUp();

            ////////////////////////////////////////////////////////
            // CHANGE CHARACTER ////////////////////////////////////
            ////////////////////////////////////////////////////////
            if (!game.isFinished()) {
                if (game.getCurrentCharacter() == Game.GameCharacter.Cross) {
                    game.setCurrentCharacter(Game.GameCharacter.Circle);
                } else if (game.getCurrentCharacter() == Game.GameCharacter.Circle) {
                    game.setCurrentCharacter(Game.GameCharacter.Cross);
                }
            }
        }
        ////////////////////////////////////////////////////////
        // COMPUTER ////////////////////////////////////////////
        ////////////////////////////////////////////////////////
        if (!game.isFinished() && game.isComputer()) {
            Random r = new Random();

            ArrayList<Point> availableCells = getAvailableCells(game);

            int num = r.nextInt(availableCells.size());

            Point computerPoint = availableCells.get(num);

            getCell(computerPoint.x, computerPoint.y).setGameCharacter(game.getCurrentCharacter());

            game.setCurrentX(computerPoint.x);
            game.setCurrentY(computerPoint.y);

            game.stepUp();

            ////////////////////////////////////////////////////////
            // CHANGE CHARACTER ////////////////////////////////////
            ////////////////////////////////////////////////////////
            if (!game.isFinished()) {
                if (game.getCurrentCharacter() == Game.GameCharacter.Cross) {
                    game.setCurrentCharacter(Game.GameCharacter.Circle);
                } else if (game.getCurrentCharacter() == Game.GameCharacter.Circle) {
                    game.setCurrentCharacter(Game.GameCharacter.Cross);
                }
            }
        }
    }

    private void loadCellsFromHistory() {
        for (Game.GameState state : game.getGameHistory()) {
            TicTacToeCell cell = getCell(state.getX(), state.getY());
            cell.setGameCharacter(state.getGameCharacter());
        }
    }

    private TicTacToeCell getCell(int i, int j) {
        for (Component c : getComponents()) {
            if (c instanceof TicTacToeCell) {
                TicTacToeCell cell = ((TicTacToeCell) c);
                if (cell.getCellX() == i && cell.getCellY() == j) {
                    return cell;
                }
            }
        }
        return null;
    }

    private ArrayList< Point> getAvailableCells(Game game) {
        ArrayList< Point> arr = new ArrayList<>();

        for (int i = 0; i < game.getWidth(); i++) {
            for (int j = 0; j < game.getHeight(); j++) {
                if (game.getField()[i][j] == Game.GameCharacter.Empty || game.getField()[i][j] == null) {
                    arr.add(new Point(i, j));
                }
            }
        }
        return arr;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridBagLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    void disableField() {
        for (int i = 0; i < this.getComponents().length; i++) {
            this.getComponent(i).setEnabled(false);
        }
    }
}
