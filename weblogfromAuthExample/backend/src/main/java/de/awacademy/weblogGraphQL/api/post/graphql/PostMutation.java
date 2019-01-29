package de.awacademy.weblogGraphQL.api.post.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.weblogGraphQL.api.category.Category;
import de.awacademy.weblogGraphQL.api.category.CategoryRepository;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostDao;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.services.graphql.exceptions.IdNotFoundException;
import de.awacademy.weblogGraphQL.services.security.SecurityGraphQLAspect;
import graphql.GraphQLException;
import org.springframework.stereotype.Component;

@Component
public class PostMutation implements GraphQLMutationResolver {

	private PostDao dao;
	private SecurityGraphQLAspect securityGraphQLAspect;
	private CategoryRepository categoryRepository;

	public PostMutation(PostDao dao, SecurityGraphQLAspect securityGraphQLAspect, CategoryRepository categoryRepository) {
		this.dao = dao;
		this.securityGraphQLAspect = securityGraphQLAspect;
		this.categoryRepository = categoryRepository;
	}

	public Post createPost(String title, String text) {
		User creator = securityGraphQLAspect.getCurrentUser();
		System.out.println(creator.getId());
		Post post = new Post(title, text, creator);
		return dao.create(post);
	}

	public Post updatePost(String id, String title, String text) {
		User currentUser = securityGraphQLAspect.getCurrentUser();
		return dao.updatePost(id, title, text, currentUser);
	}

	public boolean deletePost(String id) {
		User currentUser = securityGraphQLAspect.getCurrentUser();
		Post post = dao.getPost(id);
		if (!currentUser.isAdmin()) {
			throw new GraphQLException("Benutzer ist nicht berechtigt diesen Post zu löschen");
		}
		dao.deletePost(id);
		return true;
	}

	public boolean addCategoryToPost(String postId, String categoryId) {
		if (!securityGraphQLAspect.getCurrentUser().isAdmin()) {
			throw new GraphQLException("Benutzer nicht berechtigt");
		}
		Post post = dao.getPost(postId);
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new IdNotFoundException("Kategorie wurde nicht gefunden"));
		post.getCategories().add(category);
		dao.savePost(post);
		return true;
	}
}
