package com.barsoft.java_labs2.lab7.server.database_connectors;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.barsoft.java_labs2.core.AbstractDataBaseConnector;
import com.barsoft.java_labs2.core.ConnectionInfo;
import com.barsoft.java_labs2.core.IDataBaseConnector;
import com.barsoft.java_labs2.core.ConnectionInfo.DatabaseType;
import com.barsoft.java_labs2.core.forms.ConnectionJDialog;

import com.barsoft.java_labs2.lab7.entities.Room;
import com.barsoft.java_labs2.lab7.entities.User;
import com.barsoft.java_labs2.lab7.server.ConnectionHandler;
import com.barsoft.java_labs2.lab7.server.TCPServer;

import javax.swing.*;

public abstract class DatabaseConnector extends AbstractDataBaseConnector {
	protected String filter = "";

	public DatabaseConnector(com.barsoft.java_labs2.core.ConnectionInfo c) {
		super(c);
	}

	@Override
	public boolean createTables() throws SQLException {
		if (!execute("CREATE TABLE USER" + " (ID INT NOT NULL "
				+ AUTO_INCREMENT + " ,LOGIN VARCHAR(10),"
				+ " PASSWORD VARCHAR(10)," + " PRIMARY KEY (ID))"))
			return false;
		return true;
	}

	public List<User> getRegisteredtUsers() throws SQLException {
		if (!createConnection())
			return null;
		ResultSet rs = executeQuery("SELECT * FROM " + USER + "USER");
		ArrayList results = new ArrayList();
		while (rs.next()) {
			User obj = getUser(rs.getInt("id"));
			if (obj.getLogin().contains(filter))
				results.add(obj);
		}
		closeConnection();
		return results;
	}

	public User getUser(int id) throws SQLException {
		if (!createConnection())
			return null;
		ResultSet rs = executeQuery("SELECT * FROM " + USER + "USER WHERE ID="
				+ id);
		rs.next();

		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setPassword(rs.getString("password"));

		closeConnection();
		return user;
	}

	public void addUser(User bc) throws SQLException {
		String query = "INSERT INTO " + USER + "USER (LOGIN, PASSWORD) "
				+ "VALUES ( '" + bc.getLogin() + "', '" + bc.getPassword()
				+ "')";
		TCPServer.getInstance().logDB(query);
		execute(query);
	}

	public void deleteUser(int id) throws SQLException {
		String query = "DELETE FROM " + USER + "BANK_CLIENT WHERE ID = " + id;
		TCPServer.getInstance().logDB(query);
		execute(query);
	}

	public void updateUser(int id, User dt) throws SQLException {
		String query = " UPDATE " + USER + "USER" + "  SET LOGIN= '"
				+ dt.getLogin() + "'," + "  PASSWORD= '" + dt.getPassword()
				+ "'  WHERE ID = " + id;

		TCPServer.getInstance().logDB(query);
		execute(query);
	}

	public User loginUser(String login, String password) throws SQLException {
		if (!createConnection())
			return null;

		String query = "SELECT * FROM " + USER + "USER WHERE LOGIN='" + login
				+ "' AND PASSWORD='" + password + "'";

		TCPServer.getInstance().logDB(query);

		ResultSet rs = executeQuery(query);
		if (rs.next()) {

			User user = new User();
			user.setId(rs.getInt("id"));
			user.setLogin(rs.getString("login"));
			user.setPassword(rs.getString("password"));

			closeConnection();

			return user;
		}
		closeConnection();
		return null;
	}

	public static DatabaseConnector createConnector(ConnectionInfo c) {
		if (c.databaseType == DatabaseType.mysql) {
			return new DatabaseMySQLConnector(c);
		}
		return null;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

}