package com.teodoradobreva.mongodb.service;

import java.util.List;

import com.teodoradobreva.mongodb.model.User;

public interface UsersService {
	User getUserByUsername(String username);
	List<User> getUsersNotFollowed(User user);
	void follow(User follower, User followed);
	List<User> getUsersFollowed(User userFrom);
}
