package com.example.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt.domain.User;

public interface UserRepository extends JpaRepository<User,String>{
	public User findByUsernameAndPassword(String username,String password);
}
