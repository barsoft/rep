package com.barsoft.java_labs2.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public abstract class AbstractDataBaseConnector implements IDataBaseConnector {
	protected Connection connection;
	protected String databaseURL;
	protected ConnectionInfo connectionInfo;
	protected String AUTO_INCREMENT = "AUTO_INCREMENT";
	protected String USER = "";

	public AbstractDataBaseConnector(ConnectionInfo c) {
		this.connectionInfo = c;
	}

	@Override
	public void closeConnection() {
		try {
			connection.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}
	}

	public String getDatabaseURL() {
		return databaseURL;
	}
}
