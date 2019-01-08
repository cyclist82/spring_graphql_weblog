package de.awacademy.graphql.weblog.post;


import de.awacademy.graphql.weblog.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Post {

	@Id
	@NotNull
	private String id;
	@NotNull
	@Size(min = 4, message = "Bitte mindestens 4 Zeichen")
	private String title;
	@NotNull
	@Size(min = 4, message = "Bitte mindestens 4 Zeichen")
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull
	private User creator;
	@NotNull
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	@ManyToOne(fetch = FetchType.LAZY)
	private User lastModifier;

	public Post() {
		this.id = UUID.randomUUID().toString();
	}

	public Post(String title, String text, User creator) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.text = text;
		this.creator = creator;
		this.createdAt = LocalDateTime.now();
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

	public User getLastModifier() {
		return lastModifier;
	}

	public void setLastModifier(User lastModifier) {
		this.lastModifier = lastModifier;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getLastModifiedAt() {
		return lastModifiedAt;
	}
}
