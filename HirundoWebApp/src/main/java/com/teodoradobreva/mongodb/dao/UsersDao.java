package com.teodoradobreva.mongodb.dao;

import java.util.List;

import com.teodoradobreva.mongodb.model.User;

public interface UsersDao {
	User getUserByUsername(String username);
	List<User> getUsersNotFollowed(User user);
	List<User> getUsersFollowed(User userFrom);
	void follow(User follower, User toFollow);
	void unfollow(User follower, User followed);
}
