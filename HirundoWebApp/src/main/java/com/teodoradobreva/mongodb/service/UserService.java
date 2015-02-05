package com.teodoradobreva.mongodb.service;

import com.teodoradobreva.mongodb.model.User;

public interface UserService {
	boolean emailExists(String email);
	boolean usernameExists(String email);
	boolean register(User user);
}
