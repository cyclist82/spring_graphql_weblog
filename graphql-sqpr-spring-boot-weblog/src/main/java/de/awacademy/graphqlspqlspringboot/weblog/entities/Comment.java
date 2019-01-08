package de.awacademy.graphqlspqlspringboot.weblog.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Comment {

	@Id
	private String id;
	private String text;
	private String articleId;
	private String authorId;

	public Comment() {
		this.id= UUID.randomUUID().toString();
	}

	public Comment(String text, String articleId, String authorId) {
		this.id = UUID.randomUUID().toString();
		this.text = text;
		this.articleId = articleId;
		this.authorId = authorId;
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

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
}
