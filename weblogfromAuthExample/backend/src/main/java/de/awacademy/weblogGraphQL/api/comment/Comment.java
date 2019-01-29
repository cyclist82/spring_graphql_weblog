package de.awacademy.weblogGraphQL.api.comment;

import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.user.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Length(min = 3)
	@Lob
	private String text;
	private LocalDateTime createTime;
	@ManyToOne(fetch = FetchType.LAZY)
	private User creator;
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	public Comment() {
	}

	public Comment(String text, User creator, Post post) {
		this.text = text;
		this.creator = creator;
		this.post = post;
		this.createTime = LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public User getCreator() {
		return creator;
	}

	public Post getPost() {
		return post;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}
}
