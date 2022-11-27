package com.tpe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tpe.controller.StudentController;
import com.tpe.domain.Student;
import com.tpe.service.StudentService;

@Component

public class ClRunner implements CommandLineRunner /*ApplicationRunner*/ {

	
	@Autowired
	StudentService studentService;
	
	@Autowired
	StudentController controller;
	
	
    //Once application run completely, this method will be called	
	@Override
	public void run(String... args) throws Exception {
//		 List<Student> list = studentService.getAllStudent();
//		 list.forEach(s->System.out.println(s.getFirstName()+":"+s.getLastName()));
		
//		ResponseEntity<Student> response = controller.getStudent(5L);
//		
//		System.out.println(response.getBody().getFirstName());
//		System.out.println(response.getStatusCodeValue());
		
		
	}

}
