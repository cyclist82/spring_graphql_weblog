package de.awacademy.springgraphql.weblog.repositories;

import de.awacademy.springgraphql.weblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
