package de.awacademy.springgraphql.weblog.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Article {
	@Id
	private String id;
	private String title;
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	private User creator;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "article")
	private List<Comment> comments = new ArrayList<>();

	public Article() {
		this.id = UUID.randomUUID().toString();
	}

	public Article(String title, String text) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
