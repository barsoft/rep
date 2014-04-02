package com.barsoft.java_labs2.core;

public class ConnectionInfo {
	public String server;
	public String port;
	public DatabaseType databaseType;
	public String dataBaseName;
	public String driverClass;
	public String login;
	public String password;

	public static enum DatabaseType {
		mysql, derby
	}

}
