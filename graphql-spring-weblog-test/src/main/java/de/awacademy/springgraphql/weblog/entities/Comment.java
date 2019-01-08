package de.awacademy.springgraphql.weblog.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Comment {

	@Id
	private String id;
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	private Article article;
	@ManyToOne(fetch = FetchType.LAZY)
	private User creator;

	public Comment() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
}
