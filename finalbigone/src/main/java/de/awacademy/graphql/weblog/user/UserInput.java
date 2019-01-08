package de.awacademy.graphql.weblog.user;

import org.mindrot.jbcrypt.BCrypt;

public class UserInput {

	private String username;
	private String email;
	private String password;

	public UserInput(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
