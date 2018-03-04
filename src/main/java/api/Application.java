/**
 * Copyright (c) 2018, Grant Hughes, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point to the Basement Labs API
 * 
 * @author Grant Hughes
 * @see main
 */
@SpringBootApplication
public class Application {

	/**
	 * Entry point to the Basement Labs API
	 * @param args command line arguments
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}