package com.teodoradobreva.mongodb.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.teodoradobreva.mongodb.model.Message;
import com.teodoradobreva.mongodb.model.User;

public class MessageDaoImpl extends BasicDAO<Message, ObjectId> implements
		MessageDao {

	protected MessageDaoImpl(MongoClient mongoClient, Morphia morphia,
			String dbName) {
		super(mongoClient, morphia, dbName);
		this.ensureIndexes();
	}

	@Override
	public void insert(Message message) {
		this.save(message);
	}

	@Override
	public List<Message> getLastMessages(User user, List<String> hashtags,
			int count) {
		user = this.getDs().createQuery(User.class).field("username")
				.equal(user.getUsername()).get();
		List<String> usersFollowed = user.getUsersFollowed();
		List<Message> messages = new ArrayList<Message>();
		if (usersFollowed != null && !usersFollowed.isEmpty()) {
			if (hashtags != null && !hashtags.isEmpty()) {
				// TODO
			} else {
				Query<Message> getLastMessagesQuery = this.createQuery()
						.field("author").in(usersFollowed).limit(count)
						.order("-published_date");
				messages = this.find(getLastMessagesQuery).asList();
			}
		}
		return messages;
	}

}
