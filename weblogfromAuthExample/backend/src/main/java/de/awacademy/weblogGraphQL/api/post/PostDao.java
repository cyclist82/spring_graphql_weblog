package de.awacademy.weblogGraphQL.api.post;

import de.awacademy.weblogGraphQL.api.API;
import de.awacademy.weblogGraphQL.api.post.graphql.input.PostInput;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDao implements API<Post, PostInput> {

	private PostRepositoryPagingSorting postRepositoryPagingSorting;
	private PostRepository postRepository;

	public PostDao(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public Post create(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post delete(String id) {
		return null;
	}

	@Override
	public Post get(String id) {
		return null;
	}

	@Override
	public List<Post> all() {
		return postRepository.findAllByOrderByCreatedAtDesc();
	}

}
