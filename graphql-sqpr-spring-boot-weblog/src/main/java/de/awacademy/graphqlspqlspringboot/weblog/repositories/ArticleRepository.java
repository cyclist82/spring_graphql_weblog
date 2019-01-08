package de.awacademy.graphqlspqlspringboot.weblog.repositories;

import de.awacademy.graphqlspqlspringboot.weblog.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, String> {
}
