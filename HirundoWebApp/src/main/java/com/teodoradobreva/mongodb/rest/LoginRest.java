package com.teodoradobreva.mongodb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/login")
public class LoginRest {

	@GET
	@Path("/hello")
	public String sayHello() {
		return "Hello asd";
	}
	
	@GET
	@Path("/kur")
	public String kur()
	{
		return "kur";
	}

}
