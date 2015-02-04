package com.teodoradobreva.mongodb.model;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "messages")
public class Message {
	@Id
	private String id;
	
	private String author;
	
	private String content;
	
	@Property("published_place")
	private String publishedPlace;
	
	//TODO add index
	@Property("published_date")	
	private String publishedDate;
	
	//TODO add index
	@Property("hashtags")
	List<String> hashtags;
	
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

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}
	
}
