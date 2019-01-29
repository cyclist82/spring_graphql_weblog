package de.awacademy.weblogGraphQL.api.comment;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostRepository;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.services.graphql.exceptions.IdNotFoundException;
import de.awacademy.weblogGraphQL.services.security.SecurityGraphQLAspect;
import graphql.GraphQLException;
import org.springframework.stereotype.Component;

@Component
public class CommentMutation implements GraphQLMutationResolver {

	private SecurityGraphQLAspect securityGraphQLAspect;
	private PostRepository postRepository;
	private CommentRepository commentRepository;

	public CommentMutation(SecurityGraphQLAspect securityGraphQLAspect, PostRepository postRepository, CommentRepository commentRepository) {
		this.securityGraphQLAspect = securityGraphQLAspect;
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
	}

	public Comment createComment(String postId, String text) {
		User creator = securityGraphQLAspect.getCurrentUser();
		Post post = postRepository.findById(postId).orElseThrow(() -> new IdNotFoundException("Post wurde nicht gefunden"));
		Comment comment = new Comment(text, creator, post);
		return commentRepository.save(comment);
	}

	public Boolean deleteComment(String id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Kommentar konnte nicht gefunden werden."));
		User currentUser = securityGraphQLAspect.getCurrentUser();
		if (!(currentUser.isAdmin() || currentUser.getId().equals(comment.getCreator().getId()))) {
			throw new GraphQLException("Benutzer nicht berechtigt");
		}
		commentRepository.deleteById(id);
		return true;
	}

}
