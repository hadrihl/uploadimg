package com.example.uploadimg;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
public class CommonController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/upload")
	public String getUploadImagePage(Model model) {
		User user = userService.getUserByUsername("david");
		model.addAttribute("user", user);
		
		// debugging purposes
		System.out.println("image_file: " + user.getImageFile());
		
		return "index";
	}
	
	@PostMapping("/upload")
	public String uploadProfileImage(@ModelAttribute("user") User user, Model model,
			@RequestParam("file") MultipartFile file) throws IOException {

		if(file.isEmpty()) {
			model.addAttribute("err_string", "File not exist! Please choose profile image.");
			model.addAttribute("user", userService.getUserByUsername("david"));
		} else {
			User tmp = userService.updateUserInformation(user, file);
			model.addAttribute("user", tmp);
		}
		return "index";
	}
}
