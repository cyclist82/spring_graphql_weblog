package de.awacademy.weblogGraphQL.api.post.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.postOld.PostOld;
import de.awacademy.weblogGraphQL.api.postOld.PostOldRepository;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.api.user.UserRepository;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostResolver implements GraphQLResolver<Post> {

	private UserRepository userRepository;
	private PostOldRepository postOldRepository;

	public PostResolver(UserRepository userRepository, PostOldRepository postOldRepository) {
		this.userRepository = userRepository;
		this.postOldRepository = postOldRepository;
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

//	@Unsecured
//	public List<File> getFiles(Post post) {
//		return post.getFiles();
//	}

}
