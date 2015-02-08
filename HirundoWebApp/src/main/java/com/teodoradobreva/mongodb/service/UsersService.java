package com.teodoradobreva.mongodb.service;

import java.util.List;

import com.teodoradobreva.mongodb.model.User;

public interface UsersService {
	User getUserByUsername(String username);
	List<User> getUsersNotFollowed(User user);
	List<User> getUsersFollowed(User userFrom);
	void follow(User follower, User toFollow);
	void unfollow(User follower, User followed);
}
