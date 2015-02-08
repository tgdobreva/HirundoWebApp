package com.teodoradobreva.mongodb.dao;

import java.util.List;

import com.teodoradobreva.mongodb.model.User;

public interface UsersDao {

	User getUserByUsername(String username);
	List<User> getUsersNotFollowed(User user);
	void follow(User follower, User followed);
	List<User> getUsersFollowed(User userFrom);
}
