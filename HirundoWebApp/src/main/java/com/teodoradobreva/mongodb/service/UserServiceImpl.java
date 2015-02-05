package com.teodoradobreva.mongodb.service;

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
	public boolean emailExists(String email) {
		return userDao.emailExists(email);
	}

	@Override
	public boolean usernameExists(String email) {
		return userDao.usernameExists(email);
	}

	@Override
	public boolean register(User user) {
		return userDao.register(user);
	}

}
