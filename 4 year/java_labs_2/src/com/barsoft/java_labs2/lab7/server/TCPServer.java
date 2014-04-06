package com.barsoft.java_labs2.lab7.server;

import java.io.IOException;
import java.net.ServerSocket;

import com.barsoft.java_labs2.lab7.server.database_connectors.DatabaseConnector;

public class TCPServer {

	private static TCPServer instance = new TCPServer();

	public static TCPServer getInstance() {
		return instance;
	}

	private CheckersServerFrame checkersServerFrame;
	private ServerManager serverManager;

	public static void main(String[] args) throws IOException {
		getInstance().checkersServerFrame = new CheckersServerFrame();
		getInstance().checkersServerFrame.setVisible(true);
		getInstance().serverManager = new ServerManager(new ServerSocket(6789));
		getInstance().logServer("Listening socket!..");
	}

	public DatabaseConnector getDatabaseConnector() {
		return checkersServerFrame.getDatabaseJPanel().getDb();
	}

	public ServerManager getServerManager() {
		return serverManager;
	}

	private void log(String s) {
		checkersServerFrame.log(s);
	}

	public void logServer(String msg) {
		log("<<SERVER>> " + msg);
	}

	public void logClient(String msg, String hostAddress) {
		log("<<" + hostAddress + ">> " + msg);
	}

	public void logDB(String query) {
		log("<<DATABASE>> " + query);
	}
}
