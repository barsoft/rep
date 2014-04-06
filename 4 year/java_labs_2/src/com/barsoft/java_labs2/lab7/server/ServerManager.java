package com.barsoft.java_labs2.lab7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barsoft.java_labs2.lab7.entities.Checker;
import com.barsoft.java_labs2.lab7.entities.Checker.CheckerState;
import com.barsoft.java_labs2.lab7.entities.Game;
import com.barsoft.java_labs2.lab7.entities.Game.GameState;
import com.barsoft.java_labs2.lab7.entities.Room;
import com.barsoft.java_labs2.lab7.entities.User;

public class ServerManager implements Runnable {

	private ArrayList<ConnectionHandler> connectionHandlers = new ArrayList<ConnectionHandler>();

	private ArrayList<Room> rooms = new ArrayList<>();
	private ServerSocket serverSocket;

	private ArrayList<OnConnectToRoomListener> onConnectToRoomListeners = new ArrayList<OnConnectToRoomListener>();

	public ServerManager(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
		Room r = new Room();
		// r.setName("funny_room");
		rooms.add(r);

		r = new Room();
		// r.setName("sunny_room");
		rooms.add(r);

		r = new Room();
		// r.setName("gauptvachta");
		rooms.add(r);

		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			Socket connectionSocket;
			try {
				connectionSocket = serverSocket.accept();
				connectionHandlers.add(new ConnectionHandler(connectionSocket));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void logoutUser(String login) {
		for (ConnectionHandler h : connectionHandlers) {
			if (h.getUser() != null && h.getUser().getLogin().equals(login)) {
				String userName = h.getUser().getLogin();
				h.logout();
				TCPServer.getInstance().logServer(userName + " disconnected!");
				return;
			}
		}

	}

	public List<User> getOnlineUsers() {
		ArrayList<User> onlineUsers = new ArrayList<>();
		for (ConnectionHandler h : connectionHandlers) {
			if (h.getUser() != null) {
				onlineUsers.add(h.getUser());
			}
		}
		return onlineUsers;
	}

	public Room getRoom(long roomID) {
		for (Room r : rooms) {
			if (r.getId() == roomID)
				return r;
		}
		return null;
	}

	public Game getGame(long gameID) {
		for (Room r : rooms) {
			if (r.getGame() != null && r.getGame().getId() == gameID)
				return r.getGame();
		}
		return null;
	}

	public void connectToRoom(long roomID, long userID) {
		for (User u : getOnlineUsers()) {
			if (u.getId() == userID) {
				getRoom(roomID).getUsers().add(u);

				for (OnConnectToRoomListener onConnectToRoomListener : onConnectToRoomListeners) {
					onConnectToRoomListener.connect(roomID, userID);
				}
			}
		}
	}

	public void disconnectFromRoom(long roomID, long userID) {
		for (User u : getOnlineUsers()) {
			if (u.getId() == userID) {
				for (Room r : rooms) {
					if (r.getId() == roomID)
						r.getUsers().remove(u);
				}
				return;
			}
		}
	}

	public void switchTurn(long gameID, GameState gameState) {
		getGame(gameID).setGameState(gameState);

		gameState.print();

		if (getGame(gameID).getGameState().getCurrentCheckerState() == CheckerState.Black)
			getGame(gameID).getGameState().setCurrentCheckerState(
					CheckerState.White);
		else {
			getGame(gameID).getGameState().setCurrentCheckerState(
					CheckerState.Black);
		}
	}

	public void createGame(long roomID, Game game) {
		getRoom(roomID).setGame(game);
	}

	public ArrayList<ConnectionHandler> getConnectionHandlers() {
		return connectionHandlers;
	}

	public void addOnConnectToRoomListener(
			OnConnectToRoomListener onConnectToRoomListener) {
		this.onConnectToRoomListeners.add(onConnectToRoomListener);
	}

	public static interface OnConnectToRoomListener {
		public void connect(long roomID, long userID);
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
}
