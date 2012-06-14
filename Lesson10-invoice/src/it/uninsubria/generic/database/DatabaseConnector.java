package it.uninsubria.generic.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
	
	private final String username;
	private final String password;
	private final String url;
	private final String driverName;

	public DatabaseConnector(String username, String password, String url, String driverName) {
		this.username = username;
		this.password = password;
		this.url = url;
		this.driverName = driverName;
	}
	
	public Connection getConnection() {
		try {		
			// load JDBC driver
			Class.forName(driverName);
			
			// create connection
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
