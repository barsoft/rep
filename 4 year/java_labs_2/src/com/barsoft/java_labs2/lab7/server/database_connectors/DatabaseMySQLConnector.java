package com.barsoft.java_labs2.lab7.server.database_connectors;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.barsoft.java_labs2.core.ConnectionInfo;
import com.barsoft.java_labs2.core.forms.ConnectionJDialog;
import javax.swing.*;
import javax.swing.text.rtf.RTFEditorKit;

public class DatabaseMySQLConnector extends DatabaseConnector {

	private String serverURL;

	public DatabaseMySQLConnector(ConnectionInfo c) {
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

	public boolean execute(String query) throws SQLException {
		if (!createConnection())
			return false;
		Statement st = connection.createStatement();
		int res = st.executeUpdate(query);
		closeConnection();
		return true;
	}

	public ResultSet executeQuery(String query) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet res = st.executeQuery(query);
		return res;
	}
}