package com.teodoradobreva.mongodb.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class EmailExistsException extends WebApplicationException {

	private static final long serialVersionUID = -2283896218856066880L;
	private static final String MESSAGE = "A user is already registered with this email!";
	private static final Status CODE = Status.BAD_REQUEST;

	public EmailExistsException() {
		super(Response.status(CODE).entity(MESSAGE).type(MediaType.TEXT_PLAIN)
				.build());
	}
}
