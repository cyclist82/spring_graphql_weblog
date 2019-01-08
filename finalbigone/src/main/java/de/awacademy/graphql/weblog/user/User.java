package de.awacademy.graphql.weblog.user;

import de.awacademy.graphql.weblog.post.Post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

	@Id
	private String id;
	private String username;
	private String email;
	private String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
	private List<Post> posts = new ArrayList<>();


	public User() {
		this.id = UUID.randomUUID().toString();
	}

	public User(String username, String email, String password) {
		this.id = UUID.randomUUID().toString();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
}
