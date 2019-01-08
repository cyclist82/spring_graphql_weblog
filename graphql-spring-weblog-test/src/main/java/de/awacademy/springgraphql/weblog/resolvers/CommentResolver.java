package de.awacademy.springgraphql.weblog.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.springgraphql.weblog.entities.Article;
import de.awacademy.springgraphql.weblog.entities.Comment;
import de.awacademy.springgraphql.weblog.entities.User;
import de.awacademy.springgraphql.weblog.repositories.ArticleRepository;
import de.awacademy.springgraphql.weblog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CommentResolver implements GraphQLResolver<Comment> {

	private UserRepository userRepository;
	private ArticleRepository articleRepository;

	public Article getArticle(Comment comment) {
		return articleRepository.findById(comment.getArticle().getId()).get();
	}

	public User getCreator(Comment comment) {
		return userRepository.findById(comment.getCreator().getId()).get();
	}
}
