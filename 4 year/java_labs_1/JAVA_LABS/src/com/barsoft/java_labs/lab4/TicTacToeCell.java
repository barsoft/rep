/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab4;

import javax.swing.Icon;

/**
 *
 * @author Maks
 */
public class TicTacToeCell extends javax.swing.JButton {

    /**
     * Creates new form TicTacToeCell
     */
    private Icon crossIcon;
    private Icon circleIcon;
    private Game.GameCharacter gameCharacter;
    private int cellX;
    private int cellY;

    public TicTacToeCell() {
        crossIcon = new javax.swing.ImageIcon(getClass().getResource("/com/barsoft/java_labs/lab4/cross.png"));
        circleIcon = new javax.swing.ImageIcon(getClass().getResource("/com/barsoft/java_labs/lab4/circle.png"));
    }

    public Game.GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public void setGameCharacter(Game.GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
        if (gameCharacter == Game.GameCharacter.Cross) {
            this.setIcon(crossIcon);
            this.setEnabled(false);
        } else if (gameCharacter == Game.GameCharacter.Circle) {
            this.setIcon(circleIcon);
            this.setEnabled(false);
        } else {
            this.setEnabled(true);
        }
    }

    public int getCellX() {
        return cellX;
    }

    public void setCellX(int cellX) {
        this.cellX = cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public void setCellY(int cellY) {
        this.cellY = cellY;
    }
}
