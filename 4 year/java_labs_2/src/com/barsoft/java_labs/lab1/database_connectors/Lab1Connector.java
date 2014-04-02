package com.barsoft.java_labs.lab1.database_connectors;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.barsoft.java_labs.core.AbstractDataBaseConnector;
import com.barsoft.java_labs.core.ConnectionInfo;
import com.barsoft.java_labs.core.ConnectionInfo.DatabaseType;
import com.barsoft.java_labs.core.IDataBaseConnector;
import com.barsoft.java_labs.core.forms.ConnectionJDialog;
import com.barsoft.java_labs.lab1.entities.BankClient;
import com.barsoft.java_labs.lab1.entities.Currency;
import com.barsoft.java_labs.lab1.entities.DepositType;

import javax.swing.*;

public abstract class Lab1Connector extends AbstractDataBaseConnector {
	protected String filter = "";

	public Lab1Connector(ConnectionInfo c) {
		super(c);
	}

	@Override
	public boolean createTables() throws SQLException {
		if (!execute("CREATE TABLE CURRENCY" + " (ID INT NOT NULL "
				+ AUTO_INCREMENT + " ,NAME VARCHAR(10)," + " PRIMARY KEY (ID))"))
			return false;

		if (!execute("CREATE TABLE DEPOSIT_TYPE" + "(ID INT NOT NULL "
				+ AUTO_INCREMENT + ", NAME VARCHAR(10)," + " PRIMARY KEY (ID))"))
			return false;

		if (!execute("CREATE TABLE BANK_CLIENT "
				+ "(ID INT NOT NULL "
				+ AUTO_INCREMENT
				+ ", NAME VARCHAR(10), "
				+ "CURRENCY_ID INT, AMOUNT INT, "
				+ "DEPOSIT_TYPE_ID INT, PRIMARY KEY (ID),FOREIGN KEY (DEPOSIT_TYPE_ID) REFERENCES "
				+ USER + "DEPOSIT_TYPE (ID),"
				+ "FOREIGN KEY (CURRENCY_ID) REFERENCES " + USER
				+ "CURRENCY (ID))"))
			return false;

		return true;
	}

	public List<BankClient> getBankClients() throws SQLException {
		if (!createConnection())
			return null;
		ResultSet rs = executeQuery("SELECT * FROM " + USER + "BANK_CLIENT");
		ArrayList results = new ArrayList();
		while (rs.next()) {
			BankClient obj = getBankCleint(rs.getInt("id"));
			if (obj.getName().contains(filter))
				results.add(obj);
		}
		closeConnection();
		return Collections.unmodifiableList(results);
	}

	public List<Currency> getCurrencies() throws SQLException {
		if (!createConnection())
			return null;
		ResultSet rs = executeQuery("SELECT * FROM " + USER + "CURRENCY");
		ArrayList results = new ArrayList();
		while (rs.next()) {
			Currency obj = getCurrency(rs.getInt("id"));
			if (obj.getName().contains(filter))
				results.add(obj);
		}
		closeConnection();
		return Collections.unmodifiableList(results);
	}

	public List<DepositType> getDepositTypes() throws SQLException {
		if (!createConnection())
			return null;
		ResultSet rs = executeQuery("SELECT * FROM " + USER + "DEPOSIT_TYPE");
		ArrayList results = new ArrayList();
		while (rs.next()) {
			DepositType obj = getDepositType(rs.getInt("id"));
			if (obj.getName().contains(filter))
				results.add(obj);
		}
		closeConnection();
		return Collections.unmodifiableList(results);
	}

	public BankClient getBankCleint(int id) throws SQLException {
		if (!createConnection())
			return null;
		ResultSet rs = executeQuery("SELECT * FROM " + USER
				+ "BANK_CLIENT WHERE ID=" + id);
		rs.next();
		BankClient bankClient = new BankClient();
		bankClient.setId(rs.getInt("id"));
		bankClient.setName(rs.getString("name"));
		bankClient.setCurrency(getCurrency(rs.getInt("currency_id")));
		bankClient.setAmount(rs.getInt("amount"));
		bankClient.setDepositType(getDepositType(rs.getInt("deposit_type_id")));
		closeConnection();
		return bankClient;
	}

	public Currency getCurrency(int id) throws SQLException {
		if (!createConnection())
			return null;
		ResultSet rs = executeQuery("SELECT * FROM " + USER
				+ "CURRENCY WHERE ID=" + id);
		rs.next();
		Currency currency = new Currency();
		currency.setId(rs.getInt("id"));
		currency.setName(rs.getString("name"));
		closeConnection();
		return currency;
	}

	public DepositType getDepositType(int id) throws SQLException {
		if (!createConnection())
			return null;
		ResultSet rs = executeQuery("SELECT * FROM " + USER
				+ "DEPOSIT_TYPE WHERE ID=" + id);
		rs.next();
		DepositType depositType = new DepositType();
		depositType.setId(rs.getInt("id"));
		depositType.setName(rs.getString("name"));
		closeConnection();
		return depositType;
	}

	public void addBankCleint(BankClient bc) throws SQLException {
		execute("INSERT INTO " + USER
				+ "BANK_CLIENT (NAME, CURRENCY_ID, AMOUNT, DEPOSIT_TYPE_ID) \n"
				+ "VALUES ( '" + bc.getName() + "', "
				+ bc.getCurrency().getId() + ", " + bc.getAmount() + ", "
				+ bc.getDepositType().getId() + ")");
	}

	public void addCurrency(Currency c) throws SQLException {
		execute("INSERT INTO " + USER + "CURRENCY (NAME) " + "VALUES ('"
				+ c.getName() + "' )");
	}

	public void addDepositType(DepositType dt) throws SQLException {
		execute("INSERT INTO " + USER + "DEPOSIT_TYPE (NAME) \n" + "VALUES ('"
				+ dt.getName() + "' )");
	}

	public void deleteBankCleint(int id) throws SQLException {
		execute("DELETE FROM " + USER + "BANK_CLIENT WHERE ID = " + id);
	}

	public void deleteCurrency(int id) throws SQLException {
		execute("DELETE FROM " + USER + "CURRENCY WHERE ID = " + id);
	}

	public void deleteDepositType(int id) throws SQLException {
		execute("DELETE FROM " + USER + "DEPOSIT_TYPE WHERE ID = " + id);
	}

	public void updateBankCleint(int id, BankClient dt) throws SQLException {
		execute(" UPDATE " + USER + "BANK_CLIENT\n" + "  SET NAME= '"
				+ dt.getName() + "'," + "  CURRENCY_ID= "
				+ dt.getCurrency().getId() + "," + "  AMOUNT= "
				+ dt.getAmount() + "," + "  DEPOSIT_TYPE_ID= "
				+ dt.getDepositType().getId() + "  WHERE ID = " + id);
	}

	public void updateCurrency(int id, Currency c) throws SQLException {
		execute("UPDATE " + USER + "CURRENCY\n" + "  SET NAME= '" + c.getName()
				+ "'" + "  WHERE ID = " + id);
	}

	public void updateDepositType(int id, DepositType dt) throws SQLException {
		execute("UPDATE " + USER + "DEPOSIT_TYPE\n" + "  SET NAME= '"
				+ dt.getName() + "'" + "  WHERE ID = " + id);
	}

	public static Lab1Connector createConnector(ConnectionInfo c) {
		if (c.databaseType == DatabaseType.derby) {
			return new Lab1DerbyConnector(c);
		} else if (c.databaseType == DatabaseType.mysql) {
			return new Lab1MySqlConnector(c);
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