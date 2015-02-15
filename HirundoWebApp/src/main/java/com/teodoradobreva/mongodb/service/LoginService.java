package com.teodoradobreva.mongodb.service;

import com.teodoradobreva.mongodb.model.User;

public interface LoginService {
	User getUser(String email, String password);
}
