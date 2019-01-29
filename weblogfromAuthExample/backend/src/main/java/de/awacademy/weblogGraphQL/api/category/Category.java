package de.awacademy.weblogGraphQL.api.category;

import de.awacademy.weblogGraphQL.api.post.Post;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@NotNull
	@Length(min = 3, max = 20)
	private String name;
	private boolean active;
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories", cascade = CascadeType.ALL)
	private Set<Post> posts = new HashSet<>();

	public Category() {
	}

	public Category(@NotNull @Length(min = 3, max = 20) String name) {
		this.name = name;
		this.active = true;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
}
