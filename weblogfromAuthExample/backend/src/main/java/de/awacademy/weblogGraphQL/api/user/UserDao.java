package de.awacademy.weblogGraphQL.api.user;

import de.awacademy.weblogGraphQL.api.API;
import de.awacademy.weblogGraphQL.api.user.graphql.input.UserInput;
import de.awacademy.weblogGraphQL.services.graphql.exceptions.IdNotFoundException;
import graphql.GraphQLException;
import org.joda.time.LocalDateTime;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserDao implements API<User, UserInput> {

	@Autowired
	private UserRepository repository;

	@Override
	@Transactional
	public User create(User user) {
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(9)));
		return repository.save(user);
	}

	@Override
	@Transactional
	public User delete(String id) {
		User user = repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
		repository.delete(user);
		return user;
	}


	@Transactional
	public User update(String id, String username, String email, String passwordOld, String password) {
		User user = repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
		if (!BCrypt.checkpw(passwordOld, user.getPassword())) {
			throw new GraphQLException("Altes Passwort nicht korrekt!");
		}
		if (!user.getUsername().equals(username) && repository.existsByUsername(username)) {
			throw new GraphQLException("Benutzername bereits vergeben");
		}
		if (!user.getEmail().equals(email) && repository.existsByEmail(email)) {
			throw new GraphQLException("EMail bereits vergeben");
		}
		if (BCrypt.checkpw(password, user.getPassword())) {
			throw new GraphQLException("Altes und Neues Passwort sind gleich, bitte neues Wählen");
		}
		if (password.length() > 7) {
			user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(9)));
		} else if (!(password == null || password.trim().length() == 0)) {
			throw new GraphQLException("Neues Password zu kurz");
		}
		user.setUsername(username);
		user.setEmail(email);
		user.setModifiedAt(LocalDateTime.now());
		return repository.save(user);
	}

	@Override
	@Transactional
	public User get(String id) {
		return repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
	}

	@Transactional
	public User getByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new IdNotFoundException(email));
	}

	@Override
	@Transactional
	public List<User> all() {
		return repository.findAll();
	}
}
