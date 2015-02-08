package com.teodoradobreva.mongodb.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.exception.EmailExistsException;
import com.teodoradobreva.mongodb.exception.PasswordsNotEqualException;
import com.teodoradobreva.mongodb.exception.UsernameExistsException;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.service.RegistrationService;

@Path("/registration")
public class RegistrationRest {
	
    private RegistrationService registrationService;
	
	@InjectParam
	public void setRegistrationService(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@POST
	@Path("/register")
	public void registerUser(@FormParam("email") String email,
			@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("repeatedPassword") String repeatedPassword) {
		if (!passwordsEqual(password, repeatedPassword)) {
			throw new PasswordsNotEqualException();
		}
		if (emailExists(email)) {
			throw new EmailExistsException();
		}
		if (usernameExists(username)) {
			throw new UsernameExistsException();
		}
		User user = new User(email, username, password);
		save(user);
	}
	
	private void save(User user) {
		registrationService.register(user);
	}

	private boolean emailExists(String email) {
		return registrationService.emailExists(email);
	}

	private boolean usernameExists(String username) {
		return registrationService.usernameExists(username);
	}

	private boolean passwordsEqual(String password, String repeatedPassword) {
		return password.equals(repeatedPassword);
	}
	
}
