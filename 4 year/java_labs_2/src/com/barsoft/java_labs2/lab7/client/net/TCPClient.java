package com.barsoft.java_labs2.lab7.client.net;

import java.awt.EventQueue;
import java.net.Socket;

import com.barsoft.java_labs2.lab7.client.CheckersFrame;
import com.barsoft.java_labs2.lab7.entities.User;

public class TCPClient {

	private static TCPClient instance = new TCPClient();

	public static TCPClient getInstance() {
		return instance;
	}

	private User user;
	private Socket socket;
	private ClientManager clientManager;
	private RequestBuilder requestBuilder;
	private CheckersFrame checkersFrame;

	public static void main(final String argv[]) throws Exception {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					getInstance().socket = new Socket(argv[0], 6789);
					getInstance().clientManager = new ClientManager(
							getInstance().socket);
					getInstance().requestBuilder = new RequestBuilder(
							getInstance().clientManager);

				} catch (Exception e) {
					e.printStackTrace();
				}

				getInstance().checkersFrame = new CheckersFrame();
				getInstance().checkersFrame.setQuickLogin("pahomov");
				getInstance().checkersFrame.setQuickPass("pahomov");
				getInstance().checkersFrame.setVisible(true);
			}
		});
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ClientManager getClientManager() {
		return clientManager;
	}

	public RequestBuilder getRequestBuilder() {
		return requestBuilder;
	}

	public CheckersFrame getCheckersFrame() {
		return checkersFrame;
	}
}