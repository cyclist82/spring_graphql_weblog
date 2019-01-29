package de.awacademy.weblogGraphQL.api.category;

import de.awacademy.weblogGraphQL.api.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

	List<Category> findByPostsContains(Post post);

	List<Category> findByActive(Boolean active);
}
