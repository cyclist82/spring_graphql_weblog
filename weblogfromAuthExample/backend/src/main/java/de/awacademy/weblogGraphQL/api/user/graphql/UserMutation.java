package de.awacademy.weblogGraphQL.api.user.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.api.user.UserDao;
import de.awacademy.weblogGraphQL.api.user.UserRepository;
import de.awacademy.weblogGraphQL.services.security.SecurityGraphQLAspect;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import graphql.GraphQLException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

	private UserDao dao;
	private UserRepository userRepository;
	private SecurityGraphQLAspect securityGraphQLAspect;
	private EmailValidator emailValidator = EmailValidator.getInstance();

	public UserMutation(UserDao dao, UserRepository userRepository, SecurityGraphQLAspect securityGraphQLAspect) {
		this.dao = dao;
		this.userRepository = userRepository;
		this.securityGraphQLAspect = securityGraphQLAspect;
	}

	@Unsecured
	public User createUser(String username, String email, String password) {
		if (!emailValidator.isValid(email)) {
			throw new GraphQLException("Bitte gültige EMail Adresse eingeben");
		}
		if (userRepository.existsByEmail(email) || userRepository.existsByUsername(username)) {
			throw new GraphQLException("Benutzername oder EMail Adresse bereits vergeben");
		}
		if (password.length() < 8) {
			throw new GraphQLException("Passwort zu kurz, bitte mindestens 8 Zeichen");
		}
		return dao.create(new User(username, email, password));
	}

	public User delete(String id) {
		return dao.delete(id);
	}

	public User update(String id, String username, String email, String passwordOld, String password) {
		if (!emailValidator.isValid(email)) {
			throw new GraphQLException("Bitte gültige EMail Adresse eingeben");
		}
		if (!securityGraphQLAspect.getCurrentUser().getId().equals(id)) {
			throw new GraphQLException("Nicht ihr Account. Bitte neu einloggen");
		}
		return dao.update(id, username, email, passwordOld, password);
	}
}
