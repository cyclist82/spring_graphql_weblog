package de.awacademy.weblogGraphQL.api.post;

import de.awacademy.weblogGraphQL.api.API;
import de.awacademy.weblogGraphQL.api.post.graphql.PostsPagedOutput;
import de.awacademy.weblogGraphQL.api.post.graphql.input.PostInput;
import de.awacademy.weblogGraphQL.model.pagination.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostDao implements API<Post, PostInput> {

	private PostRepositoryPagingSorting postRepositoryPagingSorting;
	private PostRepository postRepository;

	public PostDao(PostRepositoryPagingSorting postRepositoryPagingSorting, PostRepository postRepository) {
		this.postRepositoryPagingSorting = postRepositoryPagingSorting;
		this.postRepository = postRepository;
	}

	@Override
	public Post create(Post post) {
		return postRepository.save(post);
	}

	@Override
	public Post delete(String id) {
		return null;
	}

	@Override
	public Post get(String id) {
		return null;
	}

	@Override
	public List<Post> all() {
		return postRepository.findAllByOrderByCreatedAtDesc();
	}

	public PostsPagedOutput allPostsPagedSorted(int page, int size, SortOrder sortOrder, String sortBy) {
		List<Post> posts = postRepositoryPagingSorting.findAll(PageRequest.of(page, size, new Sort(Sort.Direction.fromString(sortOrder.toString()), sortBy))).getContent();
		int totalPages = postRepositoryPagingSorting.findAll(PageRequest.of(page, size, new Sort(Sort.Direction.fromString(sortOrder.toString()), sortBy))).getTotalPages();
		return new PostsPagedOutput(posts, totalPages);
	}
}
