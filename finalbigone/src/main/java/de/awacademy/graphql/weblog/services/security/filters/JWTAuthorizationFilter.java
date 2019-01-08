package de.awacademy.graphql.weblog.services.security.filters;

import de.awacademy.graphql.weblog.services.security.SecurityConstants;
import de.awacademy.graphql.weblog.user.User;
import de.awacademy.graphql.weblog.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    @Autowired
    public JWTAuthorizationFilter(AuthenticationManager manager, UserRepository userRepository) {
        super(manager);
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConstants.HEADER);

        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(getAuthenticationToken(request, response));
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader(SecurityConstants.HEADER);

        if (token == null) {
            return null;
        }

        try {
            String id = Jwts.parser().setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody().getSubject();

            User user = userRepository.findById(id).get();
            if (user == null) {
                response.sendError(401);
                return null;
            }

            return new UsernamePasswordAuthenticationToken(user, user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
        } catch (SignatureException e) {
            response.sendError(401);
        }

        return null;
    }

}

