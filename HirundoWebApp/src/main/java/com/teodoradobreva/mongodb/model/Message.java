package com.teodoradobreva.mongodb.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "messages")
public class Message {
	@Id
	@Property("_id")
	private String id;
	
	private String author;
	
	private String content;
	
	@Property("published_place")
	private String publishedPlace;
	
	@Property("published_date")
	private String publishedDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublishedPlace() {
		return publishedPlace;
	}

	public void setPublishedPlace(String publishedPlace) {
		this.publishedPlace = publishedPlace;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}
	
}
