package com.teodoradobreva.mongodb.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.mongodb.morphia.Datastore;

import com.google.inject.Inject;
import com.teodoradobreva.mongodb.exception.EmailExistsException;
import com.teodoradobreva.mongodb.exception.PasswordsNotEqualException;
import com.teodoradobreva.mongodb.exception.UsernameExistsException;
import com.teodoradobreva.mongodb.model.User;

@Path("/registration")
public class RegistrationRest {
	
	@Inject
	public Datastore datastore;
	
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
	}
	
	private boolean emailExists(String email) {
		return datastore.find(User.class).filter("email", email).get() != null;
	}

	private boolean usernameExists(String username) {
		return !datastore.find(User.class, "username", username).asList().isEmpty();
	}

	private boolean passwordsEqual(String password, String repeatedPassword) {
		return password.equals(repeatedPassword);
	}
	
	
	
}
