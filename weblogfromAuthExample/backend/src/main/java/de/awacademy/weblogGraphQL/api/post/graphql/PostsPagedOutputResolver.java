package de.awacademy.weblogGraphQL.api.post.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostRepository;
import de.awacademy.weblogGraphQL.api.post.PostRepositoryPagingSorting;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostsPagedOutputResolver implements GraphQLResolver<PostsPagedOutput> {

	private PostRepository postRepository;
	private PostRepositoryPagingSorting postRepositoryPagingSorting;

	public PostsPagedOutputResolver(PostRepository postRepository, PostRepositoryPagingSorting postRepositoryPagingSorting) {
		this.postRepository = postRepository;
		this.postRepositoryPagingSorting = postRepositoryPagingSorting;
	}

	@Unsecured
	public List<Post> getPosts(PostsPagedOutput postsPagedOutput) {
		return postsPagedOutput.getPosts();
	}
}
