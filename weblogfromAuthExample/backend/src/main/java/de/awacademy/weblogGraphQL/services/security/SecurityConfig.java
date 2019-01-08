package de.awacademy.weblogGraphQL.services.security;

import de.awacademy.weblogGraphQL.api.user.UserDao;
import de.awacademy.weblogGraphQL.services.security.filters.JWTAuthenticationFilter;
import de.awacademy.weblogGraphQL.services.security.filters.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private final UserDao dao;

	public SecurityConfig(UserDao dao) {
		this.dao = dao;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/storage/**").permitAll()
				.antMatchers("/graphql").permitAll()
				.anyRequest().authenticated()
				.antMatchers("/graphiql").permitAll()
				.anyRequest().permitAll()
//                .and()
//                .formLogin()
				.and()
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), dao))
				.addFilter(new JWTAuthenticationFilter(authenticationManager()));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
