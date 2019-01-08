package de.awacademy.weblogGraphQL.api.post.graphql.input;

import de.awacademy.weblogGraphQL.api.user.User;

public class PostInput {

	private String title;
	private String text;
	private User modifier;

	public PostInput(String title, String text, User modifier) {
		this.title = title;
		this.text = text;
		this.modifier = modifier;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public User getModifier() {
		return modifier;
	}
}
