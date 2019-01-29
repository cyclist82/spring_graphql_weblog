package de.awacademy.weblogGraphQL.api.comment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

	int countCommentsByPostId(String postId);

	List<Comment> findCommentsByPostId(String postId);

	List<Comment> findCommentsByPostIdOrderByCreateTimeDesc(String postId, Pageable pageable);
}
