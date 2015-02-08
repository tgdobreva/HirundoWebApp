package com.teodoradobreva.mongodb.service;

import com.teodoradobreva.mongodb.model.User;

public interface RegistrationService {
	boolean emailExists(String email);
	boolean usernameExists(String username);
	void register(User user);
}
