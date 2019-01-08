package de.awacademy.graphqlspqlspringboot.weblog.services;


import de.awacademy.graphqlspqlspringboot.weblog.entities.User;
import de.awacademy.graphqlspqlspringboot.weblog.repositories.UserRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@GraphQLApi
public class UserService {

	private UserRepository userRepository;

	@GraphQLMutation
	public User createUser(String username, String password) {
		User user = new User(username, password);
		return userRepository.save(user);
	}
}
