package de.awacademy.graphql.weblog.services.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.awacademy.graphql.weblog.post.Post;
import de.awacademy.graphql.weblog.post.PostRepository;

import java.util.List;

public class Query implements GraphQLQueryResolver {

	private PostRepository postRepository;

	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}
}
