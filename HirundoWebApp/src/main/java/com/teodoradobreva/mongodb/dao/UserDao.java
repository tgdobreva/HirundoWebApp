package com.teodoradobreva.mongodb.dao;

import java.util.List;

import com.teodoradobreva.mongodb.model.User;

public interface UserDao {

	User getUserByUsername(String username);
	List<User> getAllUsersExcept(User user);
	void follow(User follower, User followed);
}
