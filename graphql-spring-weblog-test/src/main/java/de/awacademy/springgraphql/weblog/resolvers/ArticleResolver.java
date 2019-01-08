package de.awacademy.springgraphql.weblog.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.springgraphql.weblog.entities.Article;
import de.awacademy.springgraphql.weblog.entities.Comment;
import de.awacademy.springgraphql.weblog.entities.User;
import de.awacademy.springgraphql.weblog.repositories.CommentRepository;
import de.awacademy.springgraphql.weblog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ArticleResolver implements GraphQLResolver<Article> {

	private UserRepository userRepository;
	private CommentRepository commentRepository;

	public User getCreator(Article article) {
		return userRepository.findById(article.getCreator().getId()).get();
	}

	public List<Comment> getComments(Article article) {
		return commentRepository.getCommentsByArticleId(article.getId());
	}
}
