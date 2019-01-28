package de.awacademy.weblogGraphQL.api.postOld;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostOldQuery implements GraphQLQueryResolver {

	private PostOldRepository postOldRepository;

	public PostOldQuery(PostOldRepository postOldRepository) {
		this.postOldRepository = postOldRepository;
	}

	@Unsecured
	public List<PostOld> HistoryByPost(String postId) {
		return postOldRepository.getByPostIdOrderBySavedAtDesc(postId);
	}
}
