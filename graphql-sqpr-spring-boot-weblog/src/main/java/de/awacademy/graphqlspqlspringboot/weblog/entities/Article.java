package de.awacademy.graphqlspqlspringboot.weblog.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Article {

	@Id
	private String id;
	private String title;
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	private User author;

	public Article() {
		this.id = UUID.randomUUID().toString();
	}

	public Article(String title, String text, User author) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.text = text;
		this.author = author;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}
