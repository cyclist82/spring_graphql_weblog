package de.awacademy.graphqlspqlspringboot.weblog.repositories;

import de.awacademy.graphqlspqlspringboot.weblog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

	List<Comment> findByArticleId(String articleId);
}
