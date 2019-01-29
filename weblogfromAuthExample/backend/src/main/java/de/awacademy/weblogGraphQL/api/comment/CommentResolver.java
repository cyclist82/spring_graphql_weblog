package de.awacademy.weblogGraphQL.api.comment;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostRepository;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.api.user.UserRepository;
import de.awacademy.weblogGraphQL.services.graphql.exceptions.IdNotFoundException;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import org.springframework.stereotype.Component;

@Component
public class CommentResolver implements GraphQLResolver<Comment> {

	private UserRepository userRepository;
	private PostRepository postRepository;
	private CommentRepository commentRepository;

	public CommentResolver(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	@Unsecured
	public User getCreator(Comment comment) {
		return userRepository.findById(comment.getCreator().getId()).orElseThrow(() -> new IdNotFoundException("Ersteller des Kommentars wurde nicht gefunden"));
	}

	@Unsecured
	public Post getPost(Comment comment) {
		return postRepository.findById(comment.getPost().getId()).orElseThrow(() -> new IdNotFoundException("Post wurde nicht gefunden"));
	}

}
