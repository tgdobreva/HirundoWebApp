package com.teodoradobreva.mongodb.dao;

import com.teodoradobreva.mongodb.model.User;

public interface LoginDao {
	boolean verify(User user);
}
