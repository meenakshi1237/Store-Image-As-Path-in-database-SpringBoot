package com.example.demoappphotoappmaven;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AppController {

	@Autowired
	private ProfileRepo profileRepo;
	
	@Autowired
	private ProfileService profileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/upload")
	public String saveUser(@ModelAttribute Profile profile,@RequestParam("image") MultipartFile photofile) {
		String fileName = null;
		try {
			fileName = this.profileService.uploadImage(profile,path, photofile);
		} catch (IOException e) {
			e.printStackTrace();
			return "unsuccessful";
		}
		
		return "successful";
	}
	
	//method to serve files
	@GetMapping("/images/{imageName}")
	public void downloadImage(@PathVariable String imageName,HttpServletResponse response) throws IOException {
		InputStream resource=this.profileService.downloadImage(path, imageName);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		
		
	}
}
