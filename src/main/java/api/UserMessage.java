/**
 * Copyright (c) 2018, Grant Hughes, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package api;

/**
 * This model class serves as a data structure for user-message pairs that can
 * be converted to and from JSON objects.
 * 
 * @author Grant Hughes
 * @see getUser
 * @see getMessage
 */
public class UserMessage {

	private String user;
	private String message;

	/**
	 * Default constructor
	 */
	private UserMessage() {
	};

	/**
	 * Constructor to build user-message pair
	 * 
	 * @param user
	 *            user of user-message pair
	 * @param message
	 *            message of user-message pair
	 */
	public UserMessage(final String user, final String message) {
		this.user = user;
		this.message = message;
	}

	/**
	 * Get user of user-message pair
	 * 
	 * @return user of user-message pair
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Get message of user-message pair
	 * 
	 * @return message of user-message pair
	 */
	public String getMessage() {
		return message;
	}
}
