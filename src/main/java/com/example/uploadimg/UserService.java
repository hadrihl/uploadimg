package com.example.uploadimg;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class UserService {

	@Autowired private UserRepository repo;
	
	@Autowired private ImageService imageService;
	
	public User getUserByUsername(String username) {
		return repo.findUserByUsername(username);
	}
	
	public User updateUserInformation(User tmp, MultipartFile file) throws IOException {
		User user = repo.getReferenceById(tmp.getId());
		
		String path = imageService.uploadToLocalFileSystem(file);
		user.setImageFile(path);
		System.out.println("path: " + path);
		
		return repo.save(user);
	}
}
