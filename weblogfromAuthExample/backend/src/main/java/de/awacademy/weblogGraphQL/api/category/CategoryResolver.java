package de.awacademy.weblogGraphQL.api.category;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostRepository;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryResolver implements GraphQLResolver<Category> {

	private PostRepository postRepository;

	public CategoryResolver(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Unsecured
	public List<Post> getPosts(Category category) {
		return postRepository.findByCategoriesContains(category);
	}
}
