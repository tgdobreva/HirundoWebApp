package com.teodoradobreva.mongodb.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.google.gson.Gson;
import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.service.UsersService;

@Path("/users")
public class UsersRest {
	
	@Context 
	private HttpServletRequest request;
	
	private UsersService usersService;
	
	@InjectParam
	public void setUserService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@GET
	@Path("/get/notFollowed")
	public String getUsersNotFollowed() {
		Gson gson = new Gson();
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("loggedInUser");
		List<User> users = usersService.getUsersNotFollowed(user);
		return gson.toJson(users);
	}
	
	@GET
	@Path("/get/followed")
	public String getUsersFollowed() {
		Gson gson = new Gson();
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("loggedInUser");
		List<User> users = usersService.getUsersFollowed(user);
		return gson.toJson(users);
	}
	
	@POST
	@Path("/follow")
	public void follow(@FormParam("username") String username) {
		HttpSession session = request.getSession(true);
		User follower = (User) session.getAttribute("loggedInUser");
		User toFollow = new User();
		toFollow.setUsername(username);
		usersService.follow(follower, toFollow);
	}
	
	@POST
	@Path("/unfollow")
	public void unfollow(@FormParam("username") String username) {
		HttpSession session = request.getSession(true);
		User follower = (User) session.getAttribute("loggedInUser");
		User followed = new User();
		followed.setUsername(username);
		usersService.unfollow(follower, followed);
	}
}
