package com.tpe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpe.dto.MyResponse;
import com.tpe.dto.UserRequest;
import com.tpe.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/register")
	@PostMapping
	public ResponseEntity<MyResponse> register(@Valid @RequestBody UserRequest userRequest){
		userService.saveUser(userRequest);
		MyResponse  myResponse=new MyResponse("User registered successfully", true);
        return new ResponseEntity<>(myResponse,HttpStatus.CREATED);		
	}
	
}
