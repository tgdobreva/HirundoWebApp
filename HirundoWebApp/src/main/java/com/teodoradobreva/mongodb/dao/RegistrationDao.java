package com.teodoradobreva.mongodb.dao;

import com.teodoradobreva.mongodb.model.User;

public interface RegistrationDao {
	boolean emailExists(String email);
	boolean usernameExists(String username);
	void register(User user);
}
