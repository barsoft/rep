package com.barsoft.java_labs.lab2.core;

import java.sql.SQLException;
import java.util.List;

import com.barsoft.java_labs.lab2.entities.User;

public interface IDatabaseService {
	public List<User> getUsers() throws SQLException;

	public User getUser(int id) throws SQLException;

	public void addUser(User bc) throws SQLException;

	public void deleteUser(int id) throws SQLException;

	public void updateUser(int id, User dt) throws SQLException;

	public User loginUser(String login, String password) throws SQLException;
}
