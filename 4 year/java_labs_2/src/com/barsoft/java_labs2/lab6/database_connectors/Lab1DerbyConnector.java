package com.barsoft.java_labs2.lab6.database_connectors;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.barsoft.java_labs2.core.ConnectionInfo;
import com.barsoft.java_labs2.core.forms.ConnectionJDialog;

import javax.swing.*;
import javax.swing.text.rtf.RTFEditorKit;

public class Lab1DerbyConnector extends Lab1Connector {

	private String databaseURLcreate;

	public Lab1DerbyConnector(ConnectionInfo c) {
		super(c);
		this.databaseURL = "jdbc:" + c.databaseType + "://" + c.server + ":"
				+ c.port + "/" + connectionInfo.dataBaseName;

		this.databaseURLcreate = "jdbc:" + c.databaseType + "://" + c.server
				+ ":" + c.port + "/" + connectionInfo.dataBaseName
				+ ";create=true";

		this.AUTO_INCREMENT = "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)";
	}

	@Override
	public boolean createConnection() throws SQLException {
		try {
			Class.forName(connectionInfo.driverClass);
			System.out.println("Connecting to: " + databaseURL);
			connection = DriverManager.getConnection(databaseURL,
					connectionInfo.login, connectionInfo.password);
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
	public boolean createDatabase() throws SQLException {
		try {
			Class.forName(connectionInfo.driverClass);
			System.out.println("Connecting to: " + databaseURLcreate);
			connection = DriverManager.getConnection(databaseURLcreate,
					connectionInfo.login, connectionInfo.password);
			createTables();
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
		boolean res = st.execute(query);
		closeConnection();
		return res;
	}

	@Override
	public ResultSet executeQuery(String query) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet res = st.executeQuery(query);
		return res;
	}
}