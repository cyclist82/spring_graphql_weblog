package de.awacademy.weblogGraphQL.api.post.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostDao;
import de.awacademy.weblogGraphQL.api.post.PostRepositoryPagingSorting;
import de.awacademy.weblogGraphQL.model.pagination.SortOrder;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostQuery implements GraphQLQueryResolver {

	private PostDao dao;
	private PostRepositoryPagingSorting postRepositoryPagingSorting;

	public PostQuery(PostDao dao, PostRepositoryPagingSorting postRepositoryPagingSorting) {
		this.dao = dao;
		this.postRepositoryPagingSorting = postRepositoryPagingSorting;
	}

	@Unsecured
	public List<Post> allPosts() {
		return dao.all();
	}

	@Unsecured
	public PostsPagedOutput allPostsSorted(Integer page, Integer size, SortOrder sortOrder, String sortBy) {
		return dao.allPostsPagedSorted(page, size, sortOrder, sortBy);
	}
}
