package de.awacademy.weblogGraphQL.api.user;

import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.user.graphql.input.UserInput;
import de.awacademy.weblogGraphQL.utility.Mergeable;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User implements Mergeable<User, UserInput> {

	@Id
	private String id;

	private String username;
	private String email;
	private String password;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creator")
	private List<Post> posts = new ArrayList<>();


	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime modifiedAt;

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

	public void setId(String id) {
		this.id = id;
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

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	@Override
	public User merge(UserInput target) {
		try {
			for (Field field : target.getClass().getDeclaredFields()) {
				Field f = getClass().getDeclaredField(field.getName());
				field.setAccessible(true);


				if (field.get(target) != null) {
					if (f.get(this) != field.get(target)) {
						f.set(this, field.get(target));
					}
				}

				field.setAccessible(false);
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return this;
	}

}
