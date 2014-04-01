package com.barsoft.java_labs.core;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDataBaseConnector {
	public boolean createDatabase() throws SQLException;

	public boolean createTables() throws SQLException;

	public boolean createConnection() throws SQLException;

	public void closeConnection();

	public boolean execute(String query) throws SQLException;

	public ResultSet executeQuery(String query) throws SQLException;
}
