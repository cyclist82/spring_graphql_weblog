package de.awacademy.graphqlspqlspringboot.weblog.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

	@Id
	private String id;
	private String username;
	private String bio;
	private String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private List<Article> articles = new ArrayList<Article>();

	public User() {
		this.id = UUID.randomUUID().toString();
	}

	public User(String username, String bio) {
		this.id = UUID.randomUUID().toString();
		this.username = username;
		this.bio = bio;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
