package de.awacademy.weblogGraphQL.api.post;

import de.awacademy.weblogGraphQL.api.comment.Comment;
import de.awacademy.weblogGraphQL.api.postOld.PostOld;
import de.awacademy.weblogGraphQL.api.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Post {

	@Id
	private String id;
	private String title;
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	private User creator;
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	@ManyToOne(fetch = FetchType.LAZY)
	private User lastModifier;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<PostOld> oldPosts = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<>();
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
//	private List<File> files = new ArrayList<>();

	public Post() {
		this.id = UUID.randomUUID().toString();
		this.createdAt = LocalDateTime.now();
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getLastModifiedAt() {
		return lastModifiedAt;
	}

	public User getLastModifier() {
		return lastModifier;
	}

	public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	public void setLastModifier(User lastModifier) {
		this.lastModifier = lastModifier;
	}

//	public List<File> getFiles() {
//		return files;
//	}
//
//	public void setFiles(List<File> files) {
//		this.files = files;
//	}


	public List<PostOld> getOldPosts() {
		return oldPosts;
	}

	public void setOldPosts(List<PostOld> oldPosts) {
		this.oldPosts = oldPosts;
	}
}
