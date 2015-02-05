package com.teodoradobreva.mongodb.dao;

import com.teodoradobreva.mongodb.model.User;

public interface UserDao {
	boolean emailExists(String email);
	boolean usernameExists(String email);
	boolean register(User user);
}
