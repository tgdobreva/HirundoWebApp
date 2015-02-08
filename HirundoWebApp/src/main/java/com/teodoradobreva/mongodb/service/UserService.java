package com.teodoradobreva.mongodb.service;

import java.util.List;

import com.teodoradobreva.mongodb.model.User;

public interface UserService {
	User getUserByUsername(String username);
	List<User> getAllUsersExcept(User user);
	void follow(User follower, User followed);
}
