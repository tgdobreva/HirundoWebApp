package com.teodoradobreva.mongodb.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.google.gson.Gson;
import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.model.Message;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.service.MessageService;
import com.teodoradobreva.mongodb.util.HirundoUtilities;

@Path("/messages")
public class MessageRest {

	@Context 
	private HttpServletRequest request;
	
	@Context 
	private HttpServletResponse response;
	
	private MessageService messageService;

	@InjectParam
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@POST
	@Path("/insert")
	public void insertMessage(@FormParam("message") String content,
			@FormParam("place") String place) throws IOException {
		content = content.trim();
		if (content.length() > HirundoUtilities.MAXIMUM_MESSAGE_SIZE) {
			String error = "Message is too long!";
			response.sendRedirect(request.getContextPath() + "/message.jsp?error=" + error);
			return;
		}
		if (place != null) {
			place = place.trim();
		}
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("loggedInUser");
		String author = user.getUsername();
		Message message = new Message(author, content, place, new Date());
		messageService.insert(message);
		response.sendRedirect(request.getContextPath() + "/index.jsp?");
	}

	@POST
	@Path("/get")
	public String getLastMessages(@FormParam("hashtags") String hashtags) {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("loggedInUser");
		Gson gson = new Gson();
		List<String> hashtagsList = gson.fromJson(hashtags, ArrayList.class);
		List<Message> messages = messageService.getLastMessages(user,
				hashtagsList, HirundoUtilities.MESSAGES_TO_GET_COUNT);
		return gson.toJson(messages);
	}
}
