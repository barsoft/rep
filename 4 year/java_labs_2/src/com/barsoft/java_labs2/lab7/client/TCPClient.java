package com.barsoft.java_labs2.lab7.client;

import java.awt.EventQueue;
import java.io.*;
import java.net.*;

public class TCPClient {
	public static void main(String argv[]) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckersFrame frame = new CheckersFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static String sendRequest(String request) throws IOException {
		String response = "";

		Socket clientSocket = new Socket("localhost", 6789);
		DataOutputStream outToServer = new DataOutputStream(
				clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(
				clientSocket.getInputStream()));

		outToServer.writeBytes(request + "<EOF>");

		StringBuilder sb = new StringBuilder();
		while (!sb.toString().contains("<EOF>")) {
			sb.append((char) inFromServer.read());
		}
		response = sb.toString();

		System.out.println(response);
		clientSocket.close();
		return response;
	}
}