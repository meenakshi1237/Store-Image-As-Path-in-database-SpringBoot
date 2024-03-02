package com.example.demoappphotoappmaven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileRepo profileRepo; 
	
	@Override
	public String uploadImage(Profile profile,String path, MultipartFile file) throws IOException {
		
		//File name
		String name = file.getOriginalFilename();
		
		//Fullpath
		String filePath = path+File.separator+name;
		
		//create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//file copy
		
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		profile.setPath(filePath);
		
		profileRepo.save(profile);
		
		return name;
	}

	@Override
	public InputStream downloadImage(String path, String imageName) throws FileNotFoundException {
		String fullPath=path+File.separator+imageName;
		InputStream is=new FileInputStream(fullPath);
		//db logic to return input stream
		
		return is;
	}
}

