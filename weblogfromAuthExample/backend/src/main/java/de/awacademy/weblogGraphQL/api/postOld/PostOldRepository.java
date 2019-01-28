package de.awacademy.weblogGraphQL.api.postOld;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostOldRepository extends JpaRepository<PostOld, String> {

	List<PostOld> getByPostIdOrderBySavedAtDesc(String postId);
}
