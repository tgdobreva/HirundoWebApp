package com.teodoradobreva.mongodb.service;

import java.util.List;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.dao.UsersDao;
import com.teodoradobreva.mongodb.model.User;

public class UsersServiceImpl implements UsersService {

	private UsersDao usersDao;
	
	@InjectParam
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}
	
	@Override
	public User getUserByUsername(String username) {
		return usersDao.getUserByUsername(username);
	}

	@Override
	public List<User> getUsersNotFollowed(User user) {
		return usersDao.getUsersNotFollowed(user);
	}

	@Override
	public void follow(User follower, User followed) {
		usersDao.follow(follower, followed);
	}

	@Override
	public List<User> getUsersFollowed(User userFrom) {
		return usersDao.getUsersFollowed(userFrom);
	}

}
