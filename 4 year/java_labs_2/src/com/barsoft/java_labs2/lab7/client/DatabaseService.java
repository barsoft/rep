package com.barsoft.java_labs2.lab7.client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.barsoft.java_labs2.lab7.core.IDatabaseService;
import com.barsoft.java_labs2.lab7.entities.User;
import com.thoughtworks.xstream.XStream;

public class DatabaseService implements IDatabaseService {

	private static DatabaseService instance = new DatabaseService();

	public static DatabaseService getInstance() {
		return instance;
	}

	@Override
	public List<User> getUsers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser(User bc) throws SQLException {

	}

	@Override
	public void deleteUser(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(int id, User dt) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public User loginUser(String login, String password) throws SQLException {
		User user = null;
		try {
			XStream xstream = new XStream();
			String response = TCPClient.sendRequest("login " + login + ","
					+ password);
			user = (User) xstream.fromXML(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
