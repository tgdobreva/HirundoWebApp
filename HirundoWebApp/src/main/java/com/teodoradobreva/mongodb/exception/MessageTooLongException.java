package com.teodoradobreva.mongodb.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class MessageTooLongException extends WebApplicationException {
	
	private static final long serialVersionUID = -2283896218856066880L;
	private static final String MESSAGE = "Message must be maximum 140 symbols!";
	private static final Status CODE = Status.BAD_REQUEST;

	public MessageTooLongException() {
		super(Response.status(CODE).entity(MESSAGE).type(MediaType.TEXT_PLAIN)
				.build());
	}
}
