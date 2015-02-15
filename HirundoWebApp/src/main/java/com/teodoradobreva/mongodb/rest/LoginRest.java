package com.teodoradobreva.mongodb.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.service.LoginService;

@Path("/login")
public class LoginRest {
	
	@Context 
	private HttpServletRequest request;
	
	@Context 
	private HttpServletResponse response;
	
	private LoginService loginService;
		
	@InjectParam
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@POST
	@Path("/login")
	public void verifyUser(@FormParam("email") String email,
			@FormParam("password") String password) throws IOException, ServletException {
		User user = loginService.getUser(email, password);
		if (user == null) {
			String error = "Wrong email or password";
			response.sendRedirect(request.getContextPath() + "/login.jsp?error=" + error);
			return;
		}
		HttpSession session= request.getSession(true);
		session.setAttribute("loggedInUser", user);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	@GET
	@Path("/logout")
	public void logout() throws IOException {
		HttpSession session= request.getSession(true);
		session.removeAttribute("loggedInUser");
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/login.jsp");
	}

}
