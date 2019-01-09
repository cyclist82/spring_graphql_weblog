package de.awacademy.weblogGraphQL.api.post;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepositoryPagingSorting extends PagingAndSortingRepository<Post, String> {


}
