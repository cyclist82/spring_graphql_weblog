package de.awacademy.springgraphql.weblog.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.awacademy.springgraphql.weblog.entities.Article;
import de.awacademy.springgraphql.weblog.repositories.ArticleRepository;
import de.awacademy.springgraphql.weblog.repositories.CommentRepository;
import de.awacademy.springgraphql.weblog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

	private ArticleRepository articleRepository;
	private CommentRepository commentRepository;
	private UserRepository userRepository;

	public List<Article> getArticles(){
		return articleRepository.findAll();
	}
}
