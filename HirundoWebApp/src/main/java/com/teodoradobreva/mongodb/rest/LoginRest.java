package com.teodoradobreva.mongodb.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.exception.WrongEmailOrPasswordException;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.service.LoginService;

@Path("/login")
public class LoginRest {
	
	private LoginService loginService;
		
	@InjectParam
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@POST
	@Path("/login")
	public void verifyUser(@FormParam("email") String email,
			@FormParam("password") String password) {
		User user = new User(email, password);
		user.setUsername("tgdobreva");
		if (!loginService.verify(user)) {
			throw new WrongEmailOrPasswordException();
		}
	}

}
