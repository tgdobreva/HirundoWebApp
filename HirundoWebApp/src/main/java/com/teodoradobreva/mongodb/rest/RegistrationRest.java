package com.teodoradobreva.mongodb.rest;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.service.RegistrationService;

@Path("/registration")
public class RegistrationRest {
	
	@Context 
	private HttpServletRequest request;
	
	@Context 
	private HttpServletResponse response;
	
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
			@FormParam("repeatedPassword") String repeatedPassword) throws IOException {
		String error = null;
		if (!passwordsEqual(password, repeatedPassword)) {
			error = "Passwords are not equal";
		}
		else if (emailExists(email)) {
			error = "Email already exists";
		}
		else if (usernameExists(username)) {
			error = "Username already exists";
		}
		if (error != null) {
			response.sendRedirect(request.getContextPath() + "/register.jsp?error=" + error);
			return;
		}
		User user = new User(email, username, password);
		user.setRegistrationDate(new Date());
		save(user);
		HttpSession session= request.getSession(true);
		session.setAttribute("loggedInUser", user);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
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
