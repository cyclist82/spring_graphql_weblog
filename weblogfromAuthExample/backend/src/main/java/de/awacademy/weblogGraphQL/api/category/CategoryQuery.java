package de.awacademy.weblogGraphQL.api.category;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryQuery implements GraphQLQueryResolver {

	private CategoryRepository categoryRepository;

	public CategoryQuery(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Unsecured
	public List<Category> getCategoriesByActive(boolean active) {
		return categoryRepository.findByActive(active);
	}

}
