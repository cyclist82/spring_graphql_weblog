package de.awacademy.weblogGraphQL.services.security;

import de.awacademy.weblogGraphQL.api.user.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class SecurityGraphQLAspect {

    /**
     * All graphQLResolver methods can be called only by authenticated user.
     * @Unsecured annotated methods are excluded
     */
    @Before("allGraphQLResolverMethods() && isDefinedInApplication() && !isMethodAnnotatedAsUnsecured()")
    public void doSecurityCheck() {
        if (SecurityContextHolder.getContext() == null ||
                SecurityContextHolder.getContext().getAuthentication() == null ||
                !SecurityContextHolder.getContext().getAuthentication().isAuthenticated() ||
                AnonymousAuthenticationToken.class.isAssignableFrom(SecurityContextHolder.getContext().getAuthentication().getClass())) {
            throw new AccessDeniedException("User not authenticated");
        }
    }

    public User getCurrentUser(){
        doSecurityCheck();
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }


    /**
     * Matches all beans that implement {@link com.coxautodev.graphql.tools.GraphQLResolver}
     * note: {@code GraphQLMutationResolver}, {@code GraphQLQueryResolver} etc
     * extend base GraphQLResolver interface
     */
    @Pointcut("target(com.coxautodev.graphql.tools.GraphQLResolver)")
    private void allGraphQLResolverMethods() {
    }

    /**
     * Matches all beans in com.mi3o.springgraphqlsecurity package
     * resolvers must be in this package (subpackages)
     */
    @Pointcut("within(de.awacademy.weblogGraphQL..*)")
    private void isDefinedInApplication() {
    }

    /**
     * Any method annotated with @Unsecured
     */
    @Pointcut("@annotation(de.awacademy.weblogGraphQL.services.security.Unsecured)")
    private void isMethodAnnotatedAsUnsecured() {
    }
}
