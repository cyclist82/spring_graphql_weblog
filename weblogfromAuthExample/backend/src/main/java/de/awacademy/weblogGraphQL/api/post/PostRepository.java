package de.awacademy.weblogGraphQL.api.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

	List<Post> findByCreatorId(String creatorId);
}
