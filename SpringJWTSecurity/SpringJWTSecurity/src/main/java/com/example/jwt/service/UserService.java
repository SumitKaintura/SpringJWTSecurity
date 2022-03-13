package com.example.jwt.service;

import java.util.List;

import com.example.jwt.domain.User;
import com.example.jwt.exception.UserNotFoundException;

public interface UserService {
	public User registerUser(User user);
	public User findByUsernameAndPassword(String username,String password)throws UserNotFoundException;
	public List<User> getAllUsers();
}
