package de.awacademy.weblogGraphQL.api.file;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileMutation implements GraphQLMutationResolver {

	private FileStorageService fileStorageService;

	public FileMutation(FileStorageService fileStorageService) {
		this.fileStorageService = fileStorageService;
	}

	public File saveFile(MultipartFile multipartFile, String postId){
		return fileStorageService.saveFile(multipartFile, postId);
	}
}
