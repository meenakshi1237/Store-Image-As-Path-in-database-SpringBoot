package com.example.demoappphotoappmaven;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {

	String uploadImage(Profile profile,String path, MultipartFile file) throws IOException;
	
	InputStream downloadImage(String path,String imageName) throws FileNotFoundException;
}
