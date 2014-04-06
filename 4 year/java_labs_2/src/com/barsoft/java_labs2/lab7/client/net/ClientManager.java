package com.barsoft.java_labs2.lab7.client.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.barsoft.java_labs2.lab7.entities.Game;
import com.barsoft.java_labs2.lab7.entities.Game.GameState;
import com.barsoft.java_labs2.lab7.entities.Room;
import com.barsoft.java_labs2.lab7.entities.User;
import com.barsoft.java_labs2.lab7.server.TCPServer;
import com.thoughtworks.xstream.XStream;

public class ClientManager implements Runnable {
	private ArrayList<Room> rooms = new ArrayList<>();
	private ArrayList<User> users = new ArrayList<>();

	private Socket socket;

	private XStream xStream = new XStream();

	private ArrayList<OnDownloadListener> onRoomsDownloadListeners = new ArrayList<OnDownloadListener>();
	private ArrayList<OnLoggedInListener> onLoggedInListeners = new ArrayList<OnLoggedInListener>();
	private ArrayList<OnGameStateUpdatedListener> onGameStateUpdatedListeners = new ArrayList<OnGameStateUpdatedListener>();
	private ArrayList<OnGameStartedListener> onGameStartedListeners = new ArrayList<OnGameStartedListener>();

	private InputStream inFromServer;
	private DataOutputStream outToServer;

	public ClientManager(Socket socket) {
		this.socket = socket;
		try {
			inFromServer = socket.getInputStream();
			outToServer = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			processServerMessage(readMessage());
		}
	}

	private void processServerMessage(String serverMessage) {
		serverMessage = serverMessage.replace("<EOF>", "");

		String roomsPattern = "^ROOMS$";
		String authenticationPattern = "^AUTHENTICATION$";
		String startGamePattern = "^START_GAME$";
		String gameStateUpdatedPattern = "^GAME_STATE_UPDATED$";

		System.out.println("Server message: " + serverMessage);

		Matcher m = Pattern.compile(authenticationPattern).matcher(
				serverMessage);
		if (m.find()) {
			String userXml = readMessage().replace("<EOF>", "");
			User user = (User) xStream.fromXML(userXml);
			for (OnLoggedInListener onLoggedInListener : onLoggedInListeners) {
				onLoggedInListener.loggedIn(user);
			}
		}

		m = Pattern.compile(roomsPattern).matcher(serverMessage);
		if (m.find()) {
			String roomsListXml = readMessage().replace("<EOF>", "");
			rooms = (ArrayList<Room>) xStream.fromXML(roomsListXml);
			for (OnDownloadListener onDownloadListener : onRoomsDownloadListeners) {
				onDownloadListener.download();
			}
		}

		m = Pattern.compile(startGamePattern).matcher(serverMessage);
		if (m.find()) {
			String gameXml = readMessage().replace("<EOF>", "");
			Game game = (Game) xStream.fromXML(gameXml);
			for (OnGameStartedListener onGameStartedListener : onGameStartedListeners) {
				onGameStartedListener.started(game);
			}
		}

		m = Pattern.compile(gameStateUpdatedPattern).matcher(serverMessage);
		if (m.find()) {
			String gameStateXml = readMessage().replace("<EOF>", "");
			GameState gameState = (GameState) xStream.fromXML(gameStateXml);
			for (OnGameStateUpdatedListener onGameStateUpdatedListener : onGameStateUpdatedListeners) {
				onGameStateUpdatedListener.updated(gameState);
			}
		}
	}

	private synchronized String readMessage() {
		StringBuilder sb = new StringBuilder();
		while (!sb.toString().contains("<EOF>")) {
			try {
				sb.append((char) inFromServer.read());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public void sendRequest(String request) throws IOException {
		outToServer.writeBytes(request + "<EOF>");
	}

	public Room getRoom(long roomID) {
		for (Room r : rooms) {
			if (r.getId() == roomID)
				return r;
		}
		return null;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void addOnRoomsDownloadListener(OnDownloadListener downloadListener) {
		this.onRoomsDownloadListeners.add(downloadListener);
	}

	public void addOnLoggedInListener(OnLoggedInListener onLoggedInListener) {
		this.onLoggedInListeners.add(onLoggedInListener);
	}

	public void addOnGameStateUpdatedListener(
			OnGameStateUpdatedListener gameStateUpdatedListener) {
		this.onGameStateUpdatedListeners.add(gameStateUpdatedListener);
	}

	public void addOnGameStartedListener(
			OnGameStartedListener onGameStartedListener) {
		this.onGameStartedListeners.add(onGameStartedListener);
	}

	public static interface OnDownloadListener {
		public void download();
	}

	public static interface OnLoggedInListener {
		public void loggedIn(User u);
	}

	public static interface OnGameStateUpdatedListener {
		public void updated(GameState gameState);
	}

	public static interface OnGameStartedListener {
		public void started(Game game);
	}
}
