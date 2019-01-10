package de.awacademy.weblogGraphQL.api.post.graphql;

import de.awacademy.weblogGraphQL.api.post.Post;

import java.util.List;

public class PostsPagedOutput {
	private List<Post> posts;
	private int amountPages;

	public PostsPagedOutput(List<Post> posts, int amountPages) {
		this.posts = posts;
		this.amountPages = amountPages;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public int getAmountPages() {
		return amountPages;
	}

}
