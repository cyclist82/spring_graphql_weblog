package de.awacademy.weblogGraphQL.api.file;

import de.awacademy.weblogGraphQL.api.post.Post;
import de.awacademy.weblogGraphQL.api.post.PostRepository;
import de.awacademy.weblogGraphQL.api.user.User;
import de.awacademy.weblogGraphQL.services.security.SecurityGraphQLAspect;
import graphql.GraphQLException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {

	private FileRepository fileRepository;
	private SecurityGraphQLAspect securityGraphQLAspect;
	private PostRepository postRepository;

	public FileStorageService(FileRepository fileRepository, SecurityGraphQLAspect securityGraphQLAspect, PostRepository postRepository) {
		this.fileRepository = fileRepository;
		this.securityGraphQLAspect = securityGraphQLAspect;
		this.postRepository = postRepository;
	}

	public File saveFile(MultipartFile multipartFile, String postId) {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		User user = securityGraphQLAspect.getCurrentUser();
		Post post = postRepository.findById(postId).orElseThrow(() -> new GraphQLException("Artikel konnte nicht gefunden werden."));
		try {
			if (fileName.contains("..")) {
				throw new GraphQLException("Dateiname enthält ungültige Zeichenfolge");
			}
			File file = new File(fileName, multipartFile.getContentType(), multipartFile.getBytes());
			file.setUser(user);
			file.setPost(post);
			return fileRepository.save(file);
		} catch (IOException e) {
			new GraphQLException(e);
		}
		return null;
	}

	public File getFileById(String fileId) {
		return fileRepository.findById(fileId)
				.orElseThrow(() -> new GraphQLException("Datei konnte nicht gefunden werden!"));
	}
}
