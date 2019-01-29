package de.awacademy.weblogGraphQL.api.category;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.weblogGraphQL.services.graphql.exceptions.IdNotFoundException;
import de.awacademy.weblogGraphQL.services.security.SecurityGraphQLAspect;
import graphql.GraphQLException;
import org.springframework.stereotype.Component;

@Component
public class CategoryMutation implements GraphQLMutationResolver {

	private SecurityGraphQLAspect securityGraphQLAspect;
	private CategoryRepository categoryRepository;

	public CategoryMutation(SecurityGraphQLAspect securityGraphQLAspect, CategoryRepository categoryRepository) {
		this.securityGraphQLAspect = securityGraphQLAspect;
		this.categoryRepository = categoryRepository;
	}

	public Category createCategory(String name) {
		if (!this.securityGraphQLAspect.getCurrentUser().isAdmin()) {
			throw new GraphQLException("Benutzer ist nicht berechtigt Kategorie zu erstellen");
		}
		return categoryRepository.save(new Category(name));
	}

	public Category toggleCategoryActive(String id) {
		Category category = categoryRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Kategorie ist nicht vorhanden"));
		category.setActive(!category.isActive());
		return categoryRepository.save(category);
	}
}
