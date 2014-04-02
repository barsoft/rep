package com.barsoft.java_labs2.lab6.database_connectors;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.barsoft.java_labs2.core.ConnectionInfo;

public class Lab1MySqlConnector extends Lab1Connector {

	private String serverURL;

	public Lab1MySqlConnector(ConnectionInfo c) {
		super(c);
		this.databaseURL = "jdbc:" + c.databaseType + "://" + c.server + ":"
				+ c.port + "/" + connectionInfo.dataBaseName;
		this.serverURL = "jdbc:" + c.databaseType + "://" + c.server + ":"
				+ c.port + "/";
		try {
			Class.forName(connectionInfo.driverClass);
		} catch (ClassNotFoundException e) {

			System.out.println("Driver class " + connectionInfo.driverClass
					+ " not found!");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public boolean createConnection() throws SQLException {
		connection = DriverManager.getConnection(databaseURL,
				connectionInfo.login, connectionInfo.password);
		return true;
	}

	@Override
	public boolean createDatabase() throws SQLException {
		try {
			Class.forName(connectionInfo.driverClass);
			System.out.println("Connecting to: " + serverURL);
			connection = DriverManager.getConnection(serverURL,
					connectionInfo.login, connectionInfo.password);

			Statement st = connection.createStatement();
			int Result = st.executeUpdate("CREATE DATABASE "
					+ connectionInfo.dataBaseName);

			createTables();
			closeConnection();
		} catch (ClassNotFoundException e) {

			System.out.println("Driver class " + connectionInfo.driverClass
					+ " not found!");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	public boolean execute(String query) throws SQLException {
		if (!createConnection())
			return false;
		Statement st = connection.createStatement();
		int res = st.executeUpdate(query);
		closeConnection();
		return true;
	}

	@Override
	public ResultSet executeQuery(String query) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet res = st.executeQuery(query);
		return res;
	}
}