package com.teodoradobreva.mongodb.service;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.dao.LoginDao;
import com.teodoradobreva.mongodb.model.User;

public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao;
	
	@InjectParam
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}

	@Override
	public User getUser(String email, String password) {
		return loginDao.getUser(email, password);
	}

}
