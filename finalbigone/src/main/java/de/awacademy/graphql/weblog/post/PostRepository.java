package de.awacademy.graphql.weblog.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

	public List<Post> findByCreatorId(String creatorID);
}
