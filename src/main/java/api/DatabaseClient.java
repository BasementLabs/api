/**
 * Copyright (c) 2018, Grant Hughes, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package api;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class serves as the data layer for the test Basement Labs application
 * back-end API. It contains static methods to add a user-message pair to the
 * application's MySQL database, and to get all user-message pair entities. It
 * also contains static methods to connect and disconnect from the database.
 * 
 * @author Grant Hughes
 * @see connect
 * @see disconnect
 * @see getMessages
 * @see insertMessage
 */
public class DatabaseClient {

	private static Connection conn = null;

	// Database credentials
	private static final String DATABASE_URL = "basement-labs-instance.chz71jxdrk7a.us-east-1.rds.amazonaws.com";
	private static final String DATABASE_PORT = "3303";
	private static final String DATABASE_NAME = "basement_labs_db_test";
	private static final String DATABASE_USERNAME = "admin";
	private static final String DATABASE_PASSWORD = "Noodles99()";

	/**
	 * Establishes connection with test Basement Labs MySQL database
	 */
	public static void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Establishing a connection to MySQL database
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + DATABASE_URL + ":" + DATABASE_PORT + "/" + DATABASE_NAME + "?useSSL=false",
					DATABASE_USERNAME, DATABASE_PASSWORD);

			System.out.println("Database connection established");

		// Connection failed
		} catch (ClassNotFoundException exception) {
			exception.printStackTrace();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Ends connection with test Basement Labs MySQL database
	 */
	public static void disconnect() {
		try {
			conn.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Gets all user-message pairs from MySQL database 
	 * @return list of all user-message pairs in MySQL
	 */
	public static List<UserMessage> getMessages() {

		// Connects to database
		connect();
		
		// List of user-message pairs to return
		List<UserMessage> userMessages = new ArrayList<UserMessage>();
		
		boolean querySuccess = false;
		try {

			// Forms and executes database query
			String query = "SELECT * FROM message";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);

			// Loop through query results
			while (result.next()) {

				// Create user-message pair and add to list
				String user = result.getString(2);
				String message = result.getString(3);
				userMessages.add(new UserMessage(user, message));
			}
			querySuccess = true;

		// Failed query
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		// Disconnects from database
		disconnect();
		
		// Returns list if successful query, otherwise null
		return querySuccess ? userMessages : null;
	}

	/**
	 * Inserts a user-message pair into the database
	 * @param user user of user-message pair
	 * @param message message of user-message pair
	 * @return user-message pair inserted into database
	 */
	public static UserMessage insertMessage(String user, String message) {

		// Connects to database
		connect();
		
		boolean querySuccess = false;
		try {

			// Forms and executes database query
			String query = "INSERT INTO message (user, message) values (?,?)";
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString(1, user);
			preparedStmt.setString(2, message);
			preparedStmt.execute();

			querySuccess = true;

		// Failed query
		} catch (SQLException exception) {
			exception.printStackTrace();
		}

		// Disconnects from database
		disconnect();
		
		// Returns inserted user-message pair if successful query, otherwise null
		return querySuccess ? new UserMessage(user, message) : null;
	}
}
