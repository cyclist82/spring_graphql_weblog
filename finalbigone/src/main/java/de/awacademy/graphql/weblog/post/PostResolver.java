package de.awacademy.graphql.weblog.post;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.graphql.weblog.user.User;
import de.awacademy.graphql.weblog.user.UserRepository;

public class PostResolver implements GraphQLResolver<Post> {

	private UserRepository userRepository;

	public PostResolver(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getCreator(Post post) {
		return userRepository.findById(post.getCreator().getId()).get();
	}
}
