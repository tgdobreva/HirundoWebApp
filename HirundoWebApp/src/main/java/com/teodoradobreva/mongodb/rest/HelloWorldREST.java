package com.teodoradobreva.mongodb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/login")
public class HelloWorldREST {

	@GET
	@Path("/hello")
	public String responseMsg() {
		return "Hello";
	}

}
