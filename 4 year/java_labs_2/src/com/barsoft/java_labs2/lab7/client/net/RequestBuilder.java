package com.barsoft.java_labs2.lab7.client.net;

import java.io.IOException;

import com.barsoft.java_labs2.lab7.entities.Game.GameState;
import com.thoughtworks.xstream.XStream;

public class RequestBuilder {

	private ClientManager clientManager;
	private XStream xStream = new XStream();

	public RequestBuilder(ClientManager dialogManager) {
		this.clientManager = dialogManager;
	}

	public void loginUser(String login, String password) {
		request("LOGIN " + login + "," + password);
	}

	public void logoutUser(String login) {
		request("LOGOUT " + login);
	}

	public void requesRegisteredtUsers() {
		request("GET_REGISTERED_USERS");
	}

	public void requestOnlineUsers() {
		request("GET_ONLINE_USERS");
	}

	public void requestRooms() {
		request("GET_ROOMS");
	}

	public void connectToRoom(long roomID, long userID) {
		request("CONNECT_TO_ROOM " + roomID + "," + userID);
	}

	public void disconnectFromRoom(long roomID, long userID) {
		request("DISCONNECT_FROM_ROOM " + roomID + "," + userID);
	}

	public void switchTurn(long gameID, GameState gameState) {
		request("SWITCH_TURN " + gameID);
		request(xStream.toXML(gameState));
	}

	private void request(String s) {
		try {
			clientManager.sendRequest(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
