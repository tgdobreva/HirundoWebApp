package com.teodoradobreva.mongodb.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.exception.EmailExistsException;
import com.teodoradobreva.mongodb.exception.PasswordsNotEqualException;
import com.teodoradobreva.mongodb.exception.UsernameExistsException;
import com.teodoradobreva.mongodb.service.UserService;

@Path("/registration")
public class RegistrationRest {
	
    private UserService userService;
	
	@InjectParam
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@POST
	@Path("/register")
	public void registerUser(@FormParam("email") String email,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("repeatedPassword") String repeatedPassword) {
		if (emailExists(email)) {
			throw new EmailExistsException();
		}
		if (usernameExists(username)) {
			throw new UsernameExistsException();
		}
		if (!passwordsEqual(password, repeatedPassword)) {
			throw new PasswordsNotEqualException();
		}
		//TODO save
		int i = 5;
	}
	
	private boolean emailExists(String email) {
		return userService.emailExists(email);
	}

	private boolean usernameExists(String username) {
		return userService.usernameExists(username);
	}

	private boolean passwordsEqual(String password, String repeatedPassword) {
		return password.equals(repeatedPassword);
	}
	
}
