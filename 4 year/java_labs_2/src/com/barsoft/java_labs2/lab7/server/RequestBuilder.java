package com.barsoft.java_labs2.lab7.server;

import java.io.IOException;
import java.util.ArrayList;

import com.barsoft.java_labs2.lab7.entities.Game;
import com.barsoft.java_labs2.lab7.entities.Game.GameState;
import com.barsoft.java_labs2.lab7.entities.Room;
import com.barsoft.java_labs2.lab7.entities.User;
import com.thoughtworks.xstream.XStream;

public class RequestBuilder {
	private ConnectionHandler connectionHandler;
	private XStream xStream = new XStream();

	public RequestBuilder(ConnectionHandler connectionHandler) {
		this.connectionHandler = connectionHandler;
	}

	public void sendGame(Game game) {
		request("START_GAME");
		if (game == null) {
			request("ERROR");
		}
		request(xStream.toXML(game));
		updateGameState(game.getGameState());
	}

	public void authentication(User user) {
		request("AUTHENTICATION");
		if (user == null) {
			request("ERROR");
		}
		request(xStream.toXML(user));
	}

	public void sendRooms(ArrayList<Room> rooms) {
		request("ROOMS");
		if (rooms == null) {
			request("ERROR");
		}
		request(xStream.toXML(rooms));
	}

	public void updateGameState(GameState newGameState) {
		request("GAME_STATE_UPDATED");
		if (newGameState == null) {
			request("ERROR");
		}
		request(xStream.toXML(newGameState));
	}

	private void request(String s) {
		try {
			connectionHandler.sendRequest(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
