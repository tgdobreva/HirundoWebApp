package com.teodoradobreva.mongodb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value = "users")
public class User {

	@Id
	private String username;

	@Indexed(value=IndexDirection.DESC, name="email", unique = true, dropDups=true) 
	private String email;

	private String password;
	@Property("registration_date")
	
	private Date registrationDate;
	
	@Property("users_followed")
	private List<String> usersFollowed;

	private boolean verified;

	public User() {
		this("", "", "", new Date(), new ArrayList<String>(), false);
	}
	
	public User(String email, String password) {
		this(email, "", password, new Date(), new ArrayList<String>(), false);
	}

	public User(String email, String username, String password) {
		this(email, username, password, null, new ArrayList<String>(), false);
	}
	
	public User(User user) {
		this(user.getEmail(), user.getUsername(), user.getPassword(),
				user.getRegistrationDate(), user.getUsersFollowed(), user.isVerified());
	}
	
	public User(String email, String username, String password,
			Date registrationDate, List<String> usersFollowed, boolean verified) {
		super();
		setEmail(email);
		setUsername(username);
		setPassword(password);
		setRegistrationDate(registrationDate);
		setUsersFollowed(usersFollowed);
		setVerified(verified);
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

}
