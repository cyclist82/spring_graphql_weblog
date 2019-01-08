package de.awacademy.weblogGraphQL.api.post;

import de.awacademy.weblogGraphQL.api.API;
import de.awacademy.weblogGraphQL.api.post.graphql.input.PostInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDao implements API<Post, PostInput> {

	@Autowired
	private PostRepository postRepository;

	@Override
	public Post create(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post delete(String id) {
		return null;
	}

	@Override
	public Post update(String id, PostInput input) {
		return null;
	}

	@Override
	public Post get(String id) {
		return null;
	}

	@Override
	public List<Post> all() {
		return null;
	}
}
