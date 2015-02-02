package com.teodoradobreva.mongodb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/login")
public class LoginRest {

	@GET
	@Path("getUser")
	public String getLoggedInUser() {
		return "asd";
	}

}
