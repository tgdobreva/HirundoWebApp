package com.teodoradobreva.mongodb.service;

import com.teodoradobreva.mongodb.model.User;

public interface LoginService {
	boolean verify(User user);
}
