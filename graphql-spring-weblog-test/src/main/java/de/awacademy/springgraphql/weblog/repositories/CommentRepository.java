package de.awacademy.springgraphql.weblog.repositories;

import de.awacademy.springgraphql.weblog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

	public List<Comment> getCommentsByCreatorId(String creatorId);

	public List<Comment> getCommentsByArticleId(String articleId);
}
