/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs.lab4;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maks
 */
@XStreamAlias("Game")
public class Game {

    @XStreamAlias("GameCharacter")
    public static enum GameCharacter {

        Empty,
        Cross,
        Circle
    }

    @XStreamAlias("GameState")
    public static class GameState {

        private GameCharacter gameCharacter;
        private int x;
        private int y;

        public GameCharacter getGameCharacter() {
            return gameCharacter;
        }

        public void setGameCharacter(GameCharacter gameCharacter) {
            this.gameCharacter = gameCharacter;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" + gameCharacter.toString() + ", " + x + ", " + y + "}";
        }
    }

    public interface GameListener {

        public void gameFinished(EventObject e);

        public void steppedUp(EventObject e);

        public void currentCharacterSet(EventObject e);
    }
    private int width = 3;
    private int height = 3;
    private int difficulty = 3;
    private ArrayList<GameState> gameHistory;
    private boolean computer;
    @XStreamOmitField
    private GameCharacter currentCharacter = GameCharacter.Circle;
    @XStreamOmitField
    private int currentX = 0;
    @XStreamOmitField
    private int currentY = 0;
    @XStreamOmitField
    private GameCharacter[][] field;
    @XStreamOmitField
    private boolean finished;
    @XStreamOmitField
    private GameCharacter winner;
    @XStreamOmitField
    private List _listeners = new ArrayList();

    public synchronized void addEventListener(GameListener listener) {
        _listeners.add(listener);
    }

    public synchronized void removeEventListener(GameListener listener) {
        _listeners.remove(listener);
    }

    private synchronized void fireGameFinishedEvent() {
        EventObject event = new EventObject(this);
        Iterator i = _listeners.iterator();
        while (i.hasNext()) {
            ((GameListener) i.next()).gameFinished(event);
        }
    }

    private synchronized void fireSteppedUpEvent() {
        EventObject event = new EventObject(this);
        Iterator i = _listeners.iterator();
        while (i.hasNext()) {
            ((GameListener) i.next()).steppedUp(event);
        }
    }

    private synchronized void fireCurrentCharacterSetEvent() {
        EventObject event = new EventObject(this);
        Iterator i = _listeners.iterator();
        while (i.hasNext()) {
            ((GameListener) i.next()).currentCharacterSet(event);
        }
    }

    public Game() {
        this.gameHistory = new ArrayList<>();
    }

    public void load(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        XStream xstream = new XStream();
        xstream.alias("Game", Game.class);
        xstream.alias("GameState", GameState.class);
        xstream.alias("GameCharacter", GameCharacter.class);
        Game game = (Game) xstream.fromXML(fis);
        this.gameHistory = game.getGameHistory();
        this.width = game.width;
        this.height = game.height;
        this.computer = game.computer;
        this.finished = false;
        this.field = new GameCharacter[width][height];

        if (gameHistory != null) {
            for (GameState state : gameHistory) {
                field[state.x][state.y] = state.gameCharacter;
            }
            if (gameHistory.size() > 0) {
                if (gameHistory.get(gameHistory.size() - 1).gameCharacter == GameCharacter.Cross) {
                    this.setCurrentCharacter(GameCharacter.Circle);
                } else {
                    this.setCurrentCharacter(GameCharacter.Cross);
                }
            }
        }

        int buff = update();
        if (buff == 1) {
            winner = GameCharacter.Circle;
            finish();
        } else if (buff == 2) {
            winner = GameCharacter.Cross;
            finish();
        } else if (buff == 3) {
            finish();
        }
    }

    public void save(String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
        xstream.toXML(this, fos);
        fos.close();
    }

    public void create(int width, int height, boolean computer,int difficulty) {
        this.gameHistory = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.computer = computer;
        this.difficulty=difficulty;
        this.finished = false;
        this.field = new GameCharacter[width][height];

        if (gameHistory != null) {
            for (GameState state : gameHistory) {
                field[state.x][state.y] = state.gameCharacter;
            }
            if (gameHistory.size() > 0) {
                if (gameHistory.get(gameHistory.size()).gameCharacter == GameCharacter.Cross) {
                    this.setCurrentCharacter(GameCharacter.Circle);
                } else {
                    this.setCurrentCharacter(GameCharacter.Cross);
                }
            }
        }
    }

    public int getCurrentX() {
        return currentX;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setCurrentCharacter(GameCharacter newCharacter) {
        this.currentCharacter = newCharacter;
        fireCurrentCharacterSetEvent();
    }

    public GameCharacter getCurrentCharacter() {
        return currentCharacter;
    }
    ////////////////////////////////////////////////////////////////////////////
    // READONLY ////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<GameState> getGameHistory() {
        return gameHistory;
    }

    public GameCharacter[][] getField() {
        return field.clone();
    }

    public GameCharacter getWinner() {
        return winner;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isComputer() {
        return computer;
    }

    public void stepUp() {
        GameState hist = new GameState();
        hist.setGameCharacter(this.currentCharacter);
        hist.setX(currentX);
        hist.setY(currentY);
        gameHistory.add(hist);

        Logger.getLogger(GameJPanel.class.getName()).log(Level.INFO, gameHistory.toString());

        field[currentX][currentY] = currentCharacter;

        int buff = update();
        if (buff == 1) {
            winner = GameCharacter.Circle;
            finish();
        } else if (buff == 2) {
            winner = GameCharacter.Cross;
            finish();
        } else if (buff == 3) {
            finish();
        }
        fireSteppedUpEvent();
    }

    public void finish() {
        finished = true;
        if (winner != null) {
            Logger.getLogger(GameJPanel.class.getName()).log(Level.INFO, winner.toString() + " wins!");
        } else {
            Logger.getLogger(GameJPanel.class.getName()).log(Level.INFO, "Draw!");
        }
        fireGameFinishedEvent();
    }

    public int update() {
        GameCharacter buff = GameCharacter.Empty;

        // Проверка по вертикали
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height - (difficulty - 1); j++) {
                if (field[i][j] != null) {//field[i][j] == field[i][j + 1] && field[i][j + 1] == field[i][j + 2] && ) {
                    boolean flag = false;
                    for (int k = 0; k < difficulty - 1; k++) {
                        if (field[i][j + k] != field[i][j + k + 1]) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        buff = field[i][j];
                        break;
                    }
                }
            }
        }

        // Проверка по горизонтали если победитель пока не найден
        if (buff == GameCharacter.Empty) {
            for (int i = 0; i < width - (difficulty - 1); i++) {
                for (int j = 0; j < height; j++) {
                    if (field[i][j] != null) {//field[i][j] == field[i][j + 1] && field[i][j + 1] == field[i][j + 2] && ) {
                        boolean flag = false;
                        for (int k = 0; k < difficulty - 1; k++) {
                            if (field[i + k][j] != field[i + k + 1][j]) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            buff = field[i][j];
                            break;
                        }
                    }
                }
            }
        }

        // Проверка главной диагонали если победитель пока не найден
        if (buff == GameCharacter.Empty) {
            for (int i = 0; i < width - (difficulty - 1); i++) {
                for (int j = 0; j < height - (difficulty - 1); j++) {
                    if (field[i][j] != null) {//field[i][j] == field[i][j + 1] && field[i][j + 1] == field[i][j + 2] && ) {
                        boolean flag = false;
                        for (int k = 0; k < difficulty - 1; k++) {
                            if (field[i + k][j + k] != field[i + k + 1][j + k + 1]) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            buff = field[i][j];
                            break;
                        }
                    }
                }
            }
        }

        // Проверка побочной диагонали если победитель пока не найден
        if (buff == GameCharacter.Empty) {
            for (int i = difficulty-1; i < width; i++) {
                for (int j = 0; j < height - (difficulty - 1); j++) {
                    if (field[i][j] != null) {//field[i][j] == field[i][j + 1] && field[i][j + 1] == field[i][j + 2] && ) {
                        boolean flag = false;
                        for (int k = 0; k < difficulty - 1; k++) {
                            if (field[i - k][j + k] != field[i - k - 1][j + k + 1]) {
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            buff = field[i][j];
                            break;
                        }
                    }
                }
            }
        }

        if (buff == GameCharacter.Circle) {
            return 1;
        }

        if (buff == GameCharacter.Cross) {
            return 2;
        }

        if (!find(GameCharacter.Empty) && !find(null)) {
            return 3;
        }

        return 0;
    }

    private boolean find(Object character) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (field[i][j] != null && field[i][j].equals(character)) {
                    return true;
                } else if (field[i][j] == character) {
                    return true;
                }
            }
        }
        return false;
    }
}
