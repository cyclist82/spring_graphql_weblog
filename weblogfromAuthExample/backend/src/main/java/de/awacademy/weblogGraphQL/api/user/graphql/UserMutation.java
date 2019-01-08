package de.awacademy.weblogGraphQL.api.user.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.api.user.UserDao;
import de.awacademy.weblogGraphQL.api.user.graphql.input.UserInput;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.GraphQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserDao dao;

    @Unsecured
    public User create(String username, String email, String password) {
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
