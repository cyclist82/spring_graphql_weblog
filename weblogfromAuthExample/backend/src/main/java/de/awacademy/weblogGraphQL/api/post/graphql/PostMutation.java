package de.awacademy.weblogGraphQL.api.post.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostDao;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.services.security.SecurityGraphQLAspect;
import graphql.GraphQLException;
import org.springframework.stereotype.Component;

@Component
public class PostMutation implements GraphQLMutationResolver {

	private PostDao dao;
	private SecurityGraphQLAspect securityGraphQLAspect;

	public PostMutation(PostDao dao, SecurityGraphQLAspect securityGraphQLAspect) {
		this.dao = dao;
		this.securityGraphQLAspect = securityGraphQLAspect;
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
		if(!currentUser.isAdmin()){
			throw new GraphQLException("Benutzer ist nicht berechtigt diesen Post zu löschen");
		}
		dao.deletePost(id);
		return true;
	}
}
