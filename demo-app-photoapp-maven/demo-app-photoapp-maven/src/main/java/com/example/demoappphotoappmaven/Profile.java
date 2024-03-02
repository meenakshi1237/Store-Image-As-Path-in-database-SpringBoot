package com.example.demoappphotoappmaven;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Profile {
	
	@Id
	private String name;
	
	private String path;

}
