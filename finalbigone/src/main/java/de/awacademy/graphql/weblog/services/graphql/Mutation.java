package de.awacademy.graphql.weblog.services.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.graphql.weblog.post.Post;
import de.awacademy.graphql.weblog.post.PostRepository;
import de.awacademy.graphql.weblog.user.User;
import de.awacademy.graphql.weblog.user.UserRepository;

public class Mutation implements GraphQLMutationResolver {

	private PostRepository postRepository;
	private UserRepository userRepository;

	public Post createPost(String title, String text, String creatorId) {
		User creator = userRepository.findById(creatorId).get();
		Post post = new Post(title, text, creator);
		return postRepository.save(post);
	}
}
