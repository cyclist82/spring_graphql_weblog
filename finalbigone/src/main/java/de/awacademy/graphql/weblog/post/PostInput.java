package de.awacademy.graphql.weblog.post;

public class PostInput {

	private String title;
	private String text;

	public PostInput(String title, String text) {
		this.title = title;
		this.text = text;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}
}
