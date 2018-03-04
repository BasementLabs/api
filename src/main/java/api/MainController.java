/**
 * Copyright (c) 2018, Grant Hughes, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package api;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class serves as the back-end API for the test Basement Labs Mobile
 * Application. It contains API end points to add a user-message pair to its
 * MySQL database, and to get all user-message pair entities.
 * 
 * @author Grant Hughes
 * @see test
 * @see getMessages
 * @see insertMessage
 */
@RestController
public class MainController {

	/**
	 * Test route for the API
	 * 
	 * @return Response Entity with "Hello World" String
	 */
	@RequestMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
	}

	/**
	 * Route to get all user-message pairs from MySQL database
	 * 
	 * @return Response Entity with JSON dump of all user-message pairs in database
	 *         or Internal Server Error
	 */
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> getMessages() {

		System.out.println("Returning JSON dump of all messages");

		// Call DatabaseClient class to execute SQL query
		final List<UserMessage> userMessages = DatabaseClient.getMessages();

		// Check for successful query
		if (userMessages != null) {
			return new ResponseEntity<>(userMessages, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Internal Server Error: Cannot connect to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Route to insert a user-message pair into MySQL database
	 * 
	 * @param requestBody
	 *            JSON of user-entity pair inserted into MySQL database
	 * @return Response Entity with JSON of user-entity pair inserted into MySQL
	 *         database
	 */
	@RequestMapping(value = "/message", method = RequestMethod.POST, consumes = "application/json")
	@CrossOrigin(origins = "*")
	public ResponseEntity<?> insertMessage(@RequestBody final UserMessage requestBody) {

		// Parsing request body
		String user = requestBody.getUser();
		String message = requestBody.getMessage();

		// Checking for null values from request body
		if (user == null || message == null) {
			return new ResponseEntity<>("Error: Bad request", HttpStatus.BAD_REQUEST);
		}

		// Putting name into correct format
		user = user.toLowerCase();
		user = user.substring(0, 1).toUpperCase() + user.substring(1);

		System.out.println("Processing " + user + "'s message: \"" + message + "\"");

		// Call DatabaseClient class to execute SQL query
		final UserMessage userMessage = DatabaseClient.insertMessage(user, message);

		// Check for successful query
		if (userMessage != null) {
			return new ResponseEntity<>(userMessage, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Internal Server Error: Cannot connect to database",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
