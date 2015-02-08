package com.teodoradobreva.mongodb.service;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.dao.RegistrationDao;
import com.teodoradobreva.mongodb.model.User;

public class RegistrationServiceImpl implements RegistrationService {

	private RegistrationDao registrationDao;
	
	@InjectParam
	public void setRegistrationDao(RegistrationDao registrationDao) {
		this.registrationDao = registrationDao;
	}
	
	@Override
	public boolean emailExists(String email) {
		return registrationDao.emailExists(email);
	}

	@Override
	public boolean usernameExists(String username) {
		return registrationDao.usernameExists(username);
	}

	@Override
	public void register(User user) {
		registrationDao.register(user);
	}

}
