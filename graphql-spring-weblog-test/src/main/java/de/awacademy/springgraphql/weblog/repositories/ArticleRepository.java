package de.awacademy.springgraphql.weblog.repositories;

import de.awacademy.springgraphql.weblog.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {

	public List<Article> getArticlesByCreatorId(String creatorId);
}
