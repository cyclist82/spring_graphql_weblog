package de.awacademy.weblogGraphQL.api.postOld;

import de.awacademy.weblogGraphQL.api.post.Post;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PostOld {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String title;
	private String text;
	private LocalDateTime savedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	public PostOld() {
	}

	public PostOld(String title, String text, Post post) {
		this.title = title;
		this.text = text;
		this.post = post;
		this.savedAt = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public LocalDateTime getSavedAt() {
		return savedAt;
	}

	public Post getPost() {
		return post;
	}


}
