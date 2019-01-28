package de.awacademy.weblogGraphQL.api.post;

import de.awacademy.weblogGraphQL.api.post.graphql.PostsPagedOutput;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.services.graphql.exceptions.IdNotFoundException;
import graphql.GraphQLException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class PostDao {

	private PostRepositoryPagingSorting postRepositoryPagingSorting;
	private PostRepository postRepository;

	public PostDao(PostRepositoryPagingSorting postRepositoryPagingSorting, PostRepository postRepository) {
		this.postRepositoryPagingSorting = postRepositoryPagingSorting;
		this.postRepository = postRepository;
	}

	public Post create(Post post) {
		return postRepository.save(post);
	}

	public boolean deletePost(String id) {
		postRepository.deleteById(id);
		return true;
	}

	public Post getPost(String id) {
		return postRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Post konnnte nicht gefunden werden"));
	}

	public List<Post> all() {
		return postRepository.findAllByOrderByCreatedAtDesc();
	}

	public PostsPagedOutput allPostsPagedSorted(int page, int size, String sortOrder, String sortBy) {
		List<Post> posts = postRepositoryPagingSorting.findAll(PageRequest.of(page, size, new Sort(Sort.Direction.fromString(sortOrder.toString()), sortBy))).getContent();
		int totalPages = postRepositoryPagingSorting.findAll(PageRequest.of(page, size, new Sort(Sort.Direction.fromString(sortOrder.toString()), sortBy))).getTotalPages();
		return new PostsPagedOutput(posts, totalPages);
	}

	public Post updatePost(String id, String title, String text, User currentUser) {
		Post post = postRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
		if (!currentUser.getId().equals(post.getCreator().getId()) && !currentUser.isAdmin()) {
			throw new GraphQLException("Nicht authorisiert den Artikel zu verändern");
		}
		post.setTitle(title);
		post.setText(text);
		post.setLastModifier(currentUser);
		post.setLastModifiedAt(LocalDateTime.now());
		return postRepository.save(post);
	}
}
