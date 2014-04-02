package com.barsoft.java_labs2.lab7.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import com.barsoft.java_labs2.lab7.entities.User;
import com.thoughtworks.xstream.XStream;

public class TCPServer {

	public TCPServer() {
		// DataBaseConnector db = DataBaseConnector.createConnector(c);
	}

	private static CheckersServerFrame csf;

	public static void main(String[] args) throws IOException {
		csf = new CheckersServerFrame();
		csf.setVisible(true);

		String request = "";
		String response;
		ServerSocket welcomeSocket = new ServerSocket(6789);

		logServer("listening socket!..");

		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(
					connectionSocket.getOutputStream());

			StringBuilder sb = new StringBuilder();
			while (!sb.toString().contains("<EOF>")) {
				sb.append((char) inFromClient.read());
			}
			request = sb.toString();

			response = processClientMessage(request) + "<EOF>";
			outToClient.writeBytes(response);

			logClient(request, connectionSocket.getInetAddress()
					.getHostAddress());
		}
	}

	public static String processClientMessage(String request) {
		request = request.replace("<EOF>", "");
		String pattern = "^login ([a-z0-9_-]{3,16}),([a-z0-9_-]{6,18})$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(request);

		if (m.find()) {
			String login = m.group(1);
			String password = m.group(2);

			if (csf.getDatabaseJPanel() != null) {
				XStream xs = new XStream();
				try {
					User u = csf.getDatabaseJPanel().getDb()
							.loginUser(login, password);
					if (u == null)
						return "wrong input data!..";
					return xs.toXML(u);
				} catch (SQLException e) {
					logServer(e.getLocalizedMessage());
					e.printStackTrace();
					return "sql exception!..";
				}
			}
			return "no database connection!..";
		}
		return "error";
	}

	private static void log(String s) {
		csf.log(s);
	}

	public static void logServer(String msg) {
		log("<<SERVER>> " + msg);
	}

	private static void logClient(String msg, String hostAddress) {
		log("<<" + hostAddress + ">> " + msg);
	}

	public static void logDB(String query) {
		log("<<DATABASE>> " + query);
	}
}
