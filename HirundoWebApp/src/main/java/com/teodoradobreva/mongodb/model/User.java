package com.teodoradobreva.mongodb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "users")
public class User {
	
	@Id
	private String username;
	
	private String email;
	
	private String password;
	@Property("registration_date")
	
	private Date registrationDate;
	@Property("users_followed")
	
	private List<String> usersFollowed;
	
	private boolean verified;
	
	private List<String> messages;
	
	public User() {
		this("", "", "", new Date(), new ArrayList<String>(), false, new ArrayList<String>());
	}
	
	public User(String email, String username, String password,
			Date registrationDate, List<String> usersFollowed,
			boolean verified, List<String> messages) {
		super();
		setEmail(email);
		setUsername(username);
		setPassword(password);
		setRegistrationDate(registrationDate);
		setUsersFollowed(usersFollowed);
		setVerified(verified);
		setMessages(messages);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public List<String> getUsersFollowed() {
		return usersFollowed;
	}

	public void setUsersFollowed(List<String> usersFollowed) {
		this.usersFollowed = usersFollowed;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
}
