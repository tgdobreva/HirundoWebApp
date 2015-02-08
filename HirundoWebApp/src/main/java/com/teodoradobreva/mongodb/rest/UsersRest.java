package com.teodoradobreva.mongodb.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.service.UsersService;

@Path("/users")
public class UsersRest {
	
	private UsersService usersService;
	
	@InjectParam
	public void setUserService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@GET
	@Path("/get/notFollowed")
	public String getUsersNotFollowed() {
		Gson gson = new Gson();
		User user = new User("tgdobreva@gmail.com", "tgdobreva", "123");
		List<User> users = usersService.getUsersNotFollowed(user);
		return gson.toJson(users);
	}
	
	@GET
	@Path("/get/followed")
	public String getUsersFollowed() {
		Gson gson = new Gson();
		User user = new User("tgdobreva@gmail.com", "tgdobreva", "123");
		List<User> users = usersService.getUsersFollowed(user);
		return gson.toJson(users);
	}
	
	@POST
	@Path("/follow")
	public void follow(@FormParam("username") String username) {
		User follower = new User("tgdobreva@gmail.com", "tgdobreva", "123");
		User toFollow = new User();
		follower.setUsername(username);
		usersService.follow(follower, toFollow);
	}
	
	@POST
	@Path("/unfollow")
	public void unfollow(@FormParam("username") String username) {
		User follower = new User("tgdobreva@gmail.com", "tgdobreva", "123");
		User followed = new User();
		follower.setUsername(username);
		usersService.unfollow(follower, followed);
	}
}
