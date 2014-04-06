/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barsoft.java_labs2.lab7.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.barsoft.java_labs2.lab7.entities.Game;
import com.barsoft.java_labs2.lab7.entities.Player;
import com.barsoft.java_labs2.lab7.entities.Room;
import com.barsoft.java_labs2.lab7.entities.User;
import com.barsoft.java_labs2.lab7.entities.Checker.CheckerState;
import com.barsoft.java_labs2.lab7.entities.Game.GameState;
import com.barsoft.java_labs2.lab7.server.ServerManager.OnConnectToRoomListener;
import com.thoughtworks.xstream.XStream;

public class ConnectionHandler implements Runnable {

	private Socket socket;

	private User user;

	private XStream xStream = new XStream();

	private RequestBuilder requestBuilder;

	private DataOutputStream outToClient;
	private BufferedReader inFromClient;

	public ConnectionHandler(Socket socket) {
		this.socket = socket;
		try {
			this.outToClient = new DataOutputStream(socket.getOutputStream());
			this.inFromClient = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.requestBuilder = new RequestBuilder(this);
		TCPServer.getInstance().getServerManager()
				.addOnConnectToRoomListener(new OnConnectToRoomListener() {

					@Override
					public void connect(long roomID, long userID) {
						if (TCPServer.getInstance().getServerManager()
								.getRoom(roomID).getUsers().size() == TCPServer
								.getInstance().getServerManager()
								.getRoom(roomID).getCapacity()) {

							User user1 = TCPServer.getInstance()
									.getServerManager().getRoom(roomID)
									.getUsers().get(0);
							User user2 = TCPServer.getInstance()
									.getServerManager().getRoom(roomID)
									.getUsers().get(1);

							Game g = null;
							if (TCPServer.getInstance().getServerManager()
									.getRoom(roomID).getGame() == null) {
								Player player1 = new Player();
								player1.setUser(user1);
								player1.setCheckerState(CheckerState.White);
								Player player2 = new Player();
								player2.setUser(user2);
								player2.setCheckerState(CheckerState.Black);
								g = new Game(player1, player2);
								TCPServer.getInstance().getServerManager()
										.createGame(roomID, g);
							} else {
								g = TCPServer.getInstance().getServerManager()
										.getRoom(roomID).getGame();
							}
							requestBuilder.sendGame(g);
						}
						if (TCPServer.getInstance().getServerManager()
								.getRoom(roomID).getUsers().size() > TCPServer
								.getInstance().getServerManager()
								.getRoom(roomID).getCapacity()) {
							for (User u : TCPServer.getInstance()
									.getServerManager().getRoom(roomID)
									.getUsers()) {
								TCPServer.getInstance().getServerManager()
										.disconnectFromRoom(roomID, u.getId());
							}
						}
					}
				});

		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				processClientMessage(readMessage());
			}
		} catch (SocketException ex) {
			TCPServer.getInstance().logClient(ex.getLocalizedMessage(),
					socket.getInetAddress().getHostAddress());
			if (user != null) {
				TCPServer.getInstance().logClient(
						user.getLogin() + " disconneceted!",
						socket.getInetAddress().getHostAddress());
			}
			TCPServer.getInstance().getServerManager().getConnectionHandlers()
					.remove(this);
			for (Room room : TCPServer.getInstance().getServerManager()
					.getRooms()) {
				if (room.getUsers().contains(user))
					room.getUsers().remove(user);
			}
		} catch (IOException ex) {
			TCPServer.getInstance().logClient(ex.getLocalizedMessage(),
					socket.getInetAddress().getHostAddress());
		}
	}

	private void processClientMessage(String request) throws IOException {
		request = request.replace("<EOF>", "");

		TCPServer.getInstance().logClient(request,
				socket.getInetAddress().getHostAddress());

		String loginPattern = "^LOGIN ([a-z0-9_-]{3,16}),([a-z0-9_-]{6,18})$";
		String logoutPattern = "^LOGOUT ([a-z0-9_-]{3,16})$";
		String getRooms = "^GET_ROOMS$";
		String connectToRoom = "^CONNECT_TO_ROOM ([0-9]{1,16}),([0-9]{1,16})$";
		String disconnectFromRoom = "^DISCONNECT_FROM_ROOM ([0-9]{1,16}),([0-9]{1,16})$";
		String switchTurn = "^SWITCH_TURN ([0-9]{1,16})$";

		Matcher m = Pattern.compile(loginPattern).matcher(request);
		if (m.find()) {
			String login = m.group(1);
			String password = m.group(2);
			User u = null;
			try {
				u = TCPServer.getInstance().getDatabaseConnector()
						.loginUser(login, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.user = u;
			requestBuilder.authentication(u);
		}

		m = Pattern.compile(logoutPattern).matcher(request);
		if (m.find()) {
			TCPServer.getInstance().getServerManager()
					.logoutUser(user.getLogin());
			return;
		}

		m = Pattern.compile(getRooms).matcher(request);
		if (m.find()) {
			ArrayList<Room> rooms = TCPServer.getInstance().getServerManager()
					.getRooms();

			requestBuilder.sendRooms(rooms);

			return;
		}

		m = Pattern.compile(connectToRoom).matcher(request);
		if (m.find()) {
			String roomID = m.group(1);
			String userID = m.group(2);

			TCPServer
					.getInstance()
					.getServerManager()
					.connectToRoom(Long.parseLong(roomID),
							Long.parseLong(userID));
			return;
		}

		m = Pattern.compile(disconnectFromRoom).matcher(request);
		if (m.find()) {
			String roomID = m.group(1);
			String userID = m.group(2);

			TCPServer
					.getInstance()
					.getServerManager()
					.disconnectFromRoom(Long.parseLong(roomID),
							Long.parseLong(userID));
			return;
		}

		m = Pattern.compile(switchTurn).matcher(request);
		if (m.find()) {
			String gameID = m.group(1);

			request = readMessage().replace("<EOF>", "");

			TCPServer
					.getInstance()
					.getServerManager()
					.switchTurn(Long.parseLong(gameID),
							(GameState) xStream.fromXML(request));

			GameState newGameState = TCPServer.getInstance().getServerManager()
					.getGame(Long.parseLong(gameID)).getGameState();

			for (ConnectionHandler connectionHandler : TCPServer.getInstance()
					.getServerManager().getConnectionHandlers()) {
				Player player1 = TCPServer.getInstance().getServerManager()
						.getGame(Long.parseLong(gameID)).getPlayer1();
				Player player2 = TCPServer.getInstance().getServerManager()
						.getGame(Long.parseLong(gameID)).getPlayer2();
				if (connectionHandler.getUser().getId() == player1.getUser()
						.getId()
						|| connectionHandler.getUser().getId() == player2
								.getUser().getId())
					connectionHandler.requestBuilder
							.updateGameState(newGameState);
			}
			return;
		}
	}

	public User getUser() {
		return user;
	}

	public void logout() {
		user = null;
	}

	private String readMessage() throws IOException {
		StringBuilder sb = new StringBuilder();
		while (!sb.toString().contains("<EOF>")) {
			sb.append((char) inFromClient.read());
		}
		return sb.toString();
	}

	public void sendRequest(String request) throws IOException {
		outToClient.writeBytes(request + "<EOF>");
	}

}
