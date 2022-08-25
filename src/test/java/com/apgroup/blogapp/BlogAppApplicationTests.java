package com.apgroup.blogapp;

import com.apgroup.blogapp.repsitory.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}
	// Testing for finding class and package of UserRepo interface
	@Test
	void repoTest(){
		String className = userRepo.getClass().getName();
//		String packName = userRepo.getClass().getPackage().getName();
		System.out.println("Class name: "+className); // +" "+ "Pack Name "+packName);
	}

}
