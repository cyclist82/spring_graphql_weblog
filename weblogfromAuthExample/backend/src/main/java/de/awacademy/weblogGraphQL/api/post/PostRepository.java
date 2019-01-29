package de.awacademy.weblogGraphQL.api.post;

import de.awacademy.weblogGraphQL.api.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

	List<Post> findByCreatorId(String creatorId);

	List<Post> findAllByOrderByCreatedAtDesc();

	List<Post> findByCategoriesContains(Category category);
}
