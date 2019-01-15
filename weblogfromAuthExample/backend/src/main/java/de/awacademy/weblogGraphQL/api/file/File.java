package de.awacademy.weblogGraphQL.api.file;

import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class File {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String fileName;
	private String fileType;
	@Lob
	private byte[] data;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	public File() {
	}

	public File(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public byte[] getData() {
		return data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
