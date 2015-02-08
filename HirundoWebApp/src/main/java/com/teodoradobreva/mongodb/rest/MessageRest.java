package com.teodoradobreva.mongodb.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.model.Message;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.service.MessageService;
import com.teodoradobreva.mongodb.util.HirundoUtilities;

@Path("/message")
public class MessageRest {

	private MessageService messageService;

	@InjectParam
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@POST
	@Path("/insert")
	public void insertMessage(@FormParam("message") String content,
			@FormParam("place") String place) {
		String author = "tgdobreva"; // TODO
		Message message = new Message(author, content, place, new Date());
		messageService.insert(message);
	}

	@POST
	@Path("/get")
	public String getLastMessages(@FormParam("hashtags") String hashtags) {
		User user = new User("tgdobreva@gmail.com", "tgdobreva", "123");//TODO
		Gson gson = new Gson();
		List<String> hashtagsList = gson.fromJson(hashtags, ArrayList.class);
		List<Message> messages = messageService.getLastMessages(user,
				hashtagsList, HirundoUtilities.MESSAGES_TO_GET_COUNT);
		return gson.toJson(messages);
	}
}
