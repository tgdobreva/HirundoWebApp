package com.teodoradobreva.mongodb.service;

import java.util.List;

import com.teodoradobreva.mongodb.model.Message;
import com.teodoradobreva.mongodb.model.User;

public interface MessageService {
	void insert(Message message);
	List<Message> getLastMessages(User user, List<String> hashtags, int count);
}
