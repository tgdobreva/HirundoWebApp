package com.teodoradobreva.mongodb.dao;

import java.util.List;

import com.teodoradobreva.mongodb.model.Message;
import com.teodoradobreva.mongodb.model.User;

public interface MessageDao {
	void insert(Message message);
	List<Message> getLastMessages(User user, List<String> hashtags, int count);
}
