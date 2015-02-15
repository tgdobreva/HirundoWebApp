package com.teodoradobreva.mongodb.dao;

import com.teodoradobreva.mongodb.model.User;

public interface LoginDao {
	User getUser(String email, String password);
}
