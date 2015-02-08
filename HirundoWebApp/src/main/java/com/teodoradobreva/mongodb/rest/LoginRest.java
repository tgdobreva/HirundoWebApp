package com.teodoradobreva.mongodb.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.sun.jersey.api.core.InjectParam;
import com.teodoradobreva.mongodb.exception.WrongEmailOrPasswordException;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.service.LoginService;
import com.teodoradobreva.mongodb.service.UserService;
import com.teodoradobreva.mongodb.util.HirundoUtilities;

@Path("/login")
public class LoginRest {

	private LoginService loginService;
	private UserService userService;
		
	@InjectParam
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}	
	
	@InjectParam
	public void setUserService(UserService userService) {
		this.userService = userService;
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
		
		//TODO
		/*List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new GrantedAuthorityImpl("ROLE_USER"));
		Authentication auth = 
			  new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(auth);*/
	}
	
	@GET
	@Path("/loggedIn")
	public String getLoggedInUser() {
		Gson gson = new Gson();
		return gson.toJson(userService.getUserByUsername("tgdobreva")); //TODO
	}

}
