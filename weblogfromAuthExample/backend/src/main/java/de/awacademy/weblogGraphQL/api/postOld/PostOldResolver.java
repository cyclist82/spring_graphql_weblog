package de.awacademy.weblogGraphQL.api.postOld;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostDao;
import org.springframework.stereotype.Component;

@Component
public class PostOldResolver implements GraphQLResolver<PostOld> {

	private PostDao postDao;

	public PostOldResolver(PostDao postDao) {
		this.postDao = postDao;
	}

	public Post getPost(PostOld postOld) {
		return postDao.getPost(postOld.getPost().getId());
	}
}
