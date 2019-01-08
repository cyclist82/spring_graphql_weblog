package de.awacademy.graphql.weblog.user;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.graphql.weblog.services.security.Unsecured;

public class UserMutation implements GraphQLMutationResolver {

	private UserRepository userRepository;

	public UserMutation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Unsecured
	public User create(String username, String email, String password) {
		return userRepository.save(new User(username, email, password));
	}
}
