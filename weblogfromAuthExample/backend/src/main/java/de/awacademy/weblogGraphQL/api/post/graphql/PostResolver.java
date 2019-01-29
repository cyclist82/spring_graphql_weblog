package de.awacademy.weblogGraphQL.api.post.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.weblogGraphQL.api.comment.Comment;
import de.awacademy.weblogGraphQL.api.comment.CommentRepository;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.postOld.PostOld;
import de.awacademy.weblogGraphQL.api.postOld.PostOldRepository;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.api.user.UserRepository;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostResolver implements GraphQLResolver<Post> {

	private UserRepository userRepository;
	private PostOldRepository postOldRepository;
	private CommentRepository commentRepository;

	public PostResolver(UserRepository userRepository, PostOldRepository postOldRepository, CommentRepository commentRepository) {
		this.userRepository = userRepository;
		this.postOldRepository = postOldRepository;
		this.commentRepository = commentRepository;
	}

	@Unsecured
	public User getCreator(Post post) {
		return userRepository.findById(post.getCreator().getId()).get();
	}

	@Unsecured
	public User getLastModifier(Post post) {
		if (post.getLastModifier() != null) {
			return userRepository.findById(post.getLastModifier().getId()).get();
		}
		return null;
	}

	@Unsecured
	public List<PostOld> getOldPosts(Post post) {
		return postOldRepository.getByPostIdOrderBySavedAtDesc(post.getId());
	}

	@Unsecured
	public int getAmountComments(Post post) {
		return commentRepository.countCommentsByPostId(post.getId());
	}

	@Unsecured
	public List<Comment> getComments(Post post) {
		return commentRepository.findCommentsByPostId(post.getId());
	}

	@Unsecured
	public List<Comment> getCommentsPaged(Post post, int page, int size) {
		return commentRepository.findCommentsByPostIdOrderByCreateTimeDesc(post.getId(), PageRequest.of(page, size));
	}

//	@Unsecured
//	public List<File> getFiles(Post post) {
//		return post.getFiles();
//	}

}
