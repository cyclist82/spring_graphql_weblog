package de.awacademy.weblogGraphQL.api.user;

import de.awacademy.weblogGraphQL.api.post.Post;
import org.joda.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

	@Id
	private String id;
	@NotNull
	@Size(min = 4)
	private String username;
	@NotNull
	@Size(min = 4)
	private String email;
	@NotNull
	private String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
	private List<Post> posts = new ArrayList<>();
	private boolean isAdmin = false;
	private boolean isSuperAdmin = false;




	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//	private List<File> files = new ArrayList<>();

	public User() {
		this.id = UUID.randomUUID().toString();
		this.createdAt = LocalDateTime.now();
	}

	public User(String username, String email, String password) {
		this.id = UUID.randomUUID().toString();
		this.createdAt = LocalDateTime.now();
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

//	public List<File> getFiles() {
//		return files;
//	}
//
//	public void setFiles(List<File> files) {
//		this.files = files;
//	}


	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		isSuperAdmin = superAdmin;
	}
}
