package com.example.jwt.service;

import java.util.Map;

import com.example.jwt.domain.User;

public interface SecurityTokenGenerator {
	 Map<String,String> generateToken(User user);
}
