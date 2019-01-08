package de.awacademy.weblogGraphQL.api.user.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.api.user.UserDao;
import de.awacademy.weblogGraphQL.api.user.UserRepository;
import de.awacademy.weblogGraphQL.api.user.graphql.input.UserInput;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    private UserDao dao;
    private UserRepository userRepository;

    public UserMutation(UserDao dao, UserRepository userRepository) {
        this.dao = dao;
        this.userRepository = userRepository;
    }

    @Unsecured
    public User createUser(String username, String email, String password) {
        if(userRepository.existsByEmail(email) || userRepository.existsByUsername(username)){
            throw new GraphQLException("Benutzername oder EMail Adresse bereits vergeben");
        }
        return dao.create(new User(username, email, password));
    }

    public User delete(String id) {
        return dao.delete(id);
    }

    @Unsecured
    public User update(String id, UserInput input, DataFetchingEnvironment environment) {
        GraphQLContext context = environment.getContext();

        return dao.update(id, input);
    }
}
