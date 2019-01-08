package de.awacademy.springgraphql.weblog.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import de.awacademy.springgraphql.weblog.entities.Article;
import de.awacademy.springgraphql.weblog.entities.Comment;
import de.awacademy.springgraphql.weblog.entities.User;
import de.awacademy.springgraphql.weblog.repositories.ArticleRepository;
import de.awacademy.springgraphql.weblog.repositories.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserResolver implements GraphQLResolver<User> {

	private CommentRepository commentRepository;
	private ArticleRepository articleRepository;

	public List<Article> getArticles(User user) {
		return articleRepository.getArticlesByCreatorId(user.getId());
	}

	public List<Comment> getComments(User user) {
		return commentRepository.getCommentsByCreatorId(user.getId());
	}
}
