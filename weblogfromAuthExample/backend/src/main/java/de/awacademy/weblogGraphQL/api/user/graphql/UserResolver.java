package de.awacademy.weblogGraphQL.api.user.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostRepository;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserResolver implements GraphQLResolver<User> {

	private PostRepository postRepository;

	public UserResolver(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Unsecured
	public List<Post> getPosts(User user) {
		return postRepository.findByCreatorId(user.getId());
	}

//	@Unsecured
//	public List<File> getFiles(User user) {
//		return user.getFiles();
//	}
}
