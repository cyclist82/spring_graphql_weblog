package de.awacademy.springgraphql.weblog.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.springgraphql.weblog.entities.Article;
import de.awacademy.springgraphql.weblog.entities.User;
import de.awacademy.springgraphql.weblog.repositories.ArticleRepository;
import de.awacademy.springgraphql.weblog.repositories.CommentRepository;
import de.awacademy.springgraphql.weblog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MutationResolver implements GraphQLMutationResolver {

	private ArticleRepository articleRepository;
	private CommentRepository commentRepository;
	private UserRepository userRepository;

	public User createUser(String username, String password) {
		User user = new User(username, password);
		return userRepository.save(user);
	}

	public Article createArticle(String title, String text, String creatorId) {
		Article article = new Article(title, text);
		User user = userRepository.findById(creatorId).get();
		article.setCreator(user);
		user.getArticles().add(article);
		userRepository.save(user);
		return articleRepository.save(article);
	}
}
