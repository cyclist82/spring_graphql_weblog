package de.awacademy.weblogGraphQL.api.user.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.api.user.UserAuth;
import de.awacademy.weblogGraphQL.api.user.UserDao;
import de.awacademy.weblogGraphQL.api.user.graphql.input.CredentialsInput;
import de.awacademy.weblogGraphQL.services.security.SecurityConstants;
import de.awacademy.weblogGraphQL.services.security.Unsecured;
import graphql.GraphQLException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserDao dao;

    @Unsecured
    public List<User> users() {
        return dao.all();
    }

    public User get(String id) {
        return dao.get(id);
    }

    @Unsecured
    public UserAuth verify(String token) {
        String id;
        try {
            id = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody().getSubject();
        } catch (SignatureException e) {
            throw new GraphQLException("Invalid JWT Token");
        }

        return new UserAuth(dao.get(id), token);
    }

    @Unsecured
    public UserAuth login(CredentialsInput input) {

        User user = dao.getByEmail(input.getEmail());

        if (!BCrypt.checkpw(input.getPassword(), user.getPassword())) {
            throw new GraphQLException("Incorrect Email or Password");
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = Jwts.builder().setSubject(user.getId())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .compact();
        return new UserAuth(user, token);
    }
}
