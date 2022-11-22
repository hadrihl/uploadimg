package com.example.uploadimg;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	public final String storageDirectoryPath = 
			"C:\\Users\\hadrihl\\Desktop\\codemonkey__\\uploadimg\\src\\main\\resources\\static\\assets\\img";
	
	public String uploadToLocalFileSystem(MultipartFile file) throws IOException {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		Path storageDirectory = Paths.get(storageDirectoryPath);
		
		if(!Files.exists(storageDirectory)) { // if the file does not exist,
			try {
				Files.createDirectories(storageDirectory);
				System.err.println("file not exist! creating one.");
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("file exist! err");
			}
		}
		
		Path destination = Paths.get(storageDirectoryPath.toString() + "\\" + fileName);
		
		// copying all bytes from an input stream to a file
		try {
			Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
}
