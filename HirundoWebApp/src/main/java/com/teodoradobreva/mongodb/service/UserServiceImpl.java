package com.teodoradobreva.mongodb.service;

import java.util.List;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.dao.UserDao;
import com.teodoradobreva.mongodb.model.User;

public class UserServiceImpl implements UserService {

private UserDao userDao;
	
	@InjectParam
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public List<User> getAllUsersExcept(User user) {
		return userDao.getAllUsersExcept(user);
	}

	@Override
	public void follow(User follower, User followed) {
		userDao.follow(follower, followed);
	}

}
