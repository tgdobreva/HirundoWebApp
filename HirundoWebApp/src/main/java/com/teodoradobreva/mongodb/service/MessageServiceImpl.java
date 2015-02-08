package com.teodoradobreva.mongodb.service;

import java.util.List;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.dao.MessageDao;
import com.teodoradobreva.mongodb.model.Message;
import com.teodoradobreva.mongodb.model.User;

public class MessageServiceImpl implements MessageService {

	private MessageDao messageDao;
	
	@InjectParam
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public void insert(Message message) {
		messageDao.insert(message);
	}

	@Override
	public List<Message> getLastMessages(User user, List<String> hashtags, int count) {
		return messageDao.getLastMessages(user, hashtags, count);
	}

}
