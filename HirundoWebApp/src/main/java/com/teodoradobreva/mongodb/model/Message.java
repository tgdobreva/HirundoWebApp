package com.teodoradobreva.mongodb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value = "messages")
public class Message {
	@Id
	private String id;

	private String author;

	private String content;

	@Property("published_place")
	private String publishedPlace;

	@Property("published_date")
	@Indexed(value=IndexDirection.DESC, name="published", unique = false, dropDups=false) 
	private Date publishedDate;

	@Property("hashtags")
	@Indexed(value=IndexDirection.DESC, name="hashtags", unique = false, dropDups=false) 
	List<String> hashtags;

	public Message() {
		this("", "", "", new Date());
	}
	
	public Message(String content,
			String publishedPlace, Date publishedDate) {
		this("", content, publishedPlace, publishedDate);
	}

	public Message(String author, String content,
			String publishedPlace, Date publishedDate) {
		setAuthor(author);
		setContent(content);
		setPublishedPlace(publishedPlace);
		setPublishedDate(publishedDate);
		List<String> hashtags = new ArrayList<String>();

		String regexPattern = "(#\\w+)";
		Pattern pattern = Pattern.compile(regexPattern);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			String hashtag = matcher.group(1);
			hashtags.add(hashtag);
		}
		setHashtags(hashtags);
	}

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

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public List<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(List<String> hashtags) {
		this.hashtags = hashtags;
	}

}
