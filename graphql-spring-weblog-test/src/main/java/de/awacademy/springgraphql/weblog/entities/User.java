package de.awacademy.springgraphql.weblog.entities;

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
	private String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
	private List<Article> articles = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
	private List<Comment> comments = new ArrayList<>();

	public User() {
		this.id = UUID.randomUUID().toString();
	}

	public User(String username, String password) {
		this.id = UUID.randomUUID().toString();
		this.username = username;
		this.password = password;
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

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
