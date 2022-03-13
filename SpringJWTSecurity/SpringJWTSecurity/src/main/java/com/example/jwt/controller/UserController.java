package com.example.jwt.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.domain.User;
import com.example.jwt.exception.UserNotFoundException;
import com.example.jwt.service.SecurityTokenGenerator;
import com.example.jwt.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	SecurityTokenGenerator securityTokenGenerator;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user)throws UserNotFoundException
	{
		ResponseEntity responseEntity;
		try
		{
			User userObj=userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
			System.out.println(userObj);
			if(userObj.getUsername().equals(user.getUsername()))
			{
				Map<String,String> tokenMap=securityTokenGenerator.generateToken(userObj);
				responseEntity=new ResponseEntity<>(tokenMap,HttpStatus.OK);
			}
			else
			{
			responseEntity=new ResponseEntity<>("Invalid User",HttpStatus.OK);
			}
		}
		catch(UserNotFoundException ue)
		{
			throw new UserNotFoundException();
		}
		catch(Exception e)
		{
			responseEntity=new ResponseEntity<>("Some other Error Occured!!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user)
	{
		User newUser=userService.registerUser(user);
		return new ResponseEntity<>("User Created",HttpStatus.CREATED);
	}
	
	@GetMapping("/api/v1/users")
	public ResponseEntity<?> getAllUsers()
	{
		List<User> userList=userService.getAllUsers();
		return new ResponseEntity<>(userList,HttpStatus.OK);
	}
}
