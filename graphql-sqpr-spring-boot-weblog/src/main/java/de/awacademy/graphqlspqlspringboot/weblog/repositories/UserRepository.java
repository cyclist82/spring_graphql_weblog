package de.awacademy.graphqlspqlspringboot.weblog.repositories;

import de.awacademy.graphqlspqlspringboot.weblog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
