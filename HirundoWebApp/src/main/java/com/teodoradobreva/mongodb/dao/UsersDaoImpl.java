package com.teodoradobreva.mongodb.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.MongoClient;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.util.HirundoUtilities;

public class UsersDaoImpl extends BasicDAO<User, ObjectId> implements UsersDao {

	protected UsersDaoImpl(MongoClient mongoClient, Morphia morphia,
			String dbName) {
		super(mongoClient, morphia, dbName);
		this.ensureIndexes();
	}

	@Override
	public User getUserByUsername(String username) {
		Query<User> getUserQuery = this.createQuery().field("username")
				.equal(username);
		return this.findOne(getUserQuery);
	}

	@Override
	public List<User> getUsersNotFollowed(User user) {
		Query<User> getUsersFollowedQuery = this.createQuery()
				.field("username").equal(user.getUsername())
				.retrievedFields(true, "users_followed");
		List<String> usersFollowedUsernames = this.find(getUsersFollowedQuery)
				.get().getUsersFollowed();
		usersFollowedUsernames.add(user.getUsername()); // not to return self
		Query<User> getUsersQuery = this.createQuery().field("username")
				.notIn(usersFollowedUsernames)
				.retrievedFields(true, "username", "verified");
		return this.find(getUsersQuery).asList();
	}

	@Override
	public void follow(User follower, User followed) {
		Query<User> getFollowerQuery = this.createQuery().field("username")
				.equal(follower.getUsername());
		UpdateOperations<User> insertUserFollowedQuery = this
				.createUpdateOperations().add("users_followed",
						followed.getUsername(), false);
		this.update(getFollowerQuery, insertUserFollowedQuery);
		this.setVerified(followed);
	}

	private void setVerified(User user) {
		Query<User> getUserVerifiedQuery = this.createQuery().field("username")
				.equal(user.getUsername()).retrievedFields(true, "verified");
		boolean isVerified = this.findOne(getUserVerifiedQuery).isVerified();
		if (!isVerified) {
			Query<User> getFollowersQuery = this.createQuery()
					.field("users_followed").equal(user.getUsername());
			long followersCount = this.count(getFollowersQuery);
			if (followersCount >= HirundoUtilities.FOLLOWERS_NEEDED_FOR_VERIFIED) {
				Query<User> getUserQuery = this.createQuery().field("username")
						.equal(user.getUsername());
				UpdateOperations<User> updateVerifiedQuery = this
						.createUpdateOperations().set("verified", true);
				this.update(getUserQuery, updateVerifiedQuery);
			}
		}
	}

	@Override
	public List<User> getUsersFollowed(User userFrom) {
		Query<User> getUsersFollowedQuery = this.createQuery()
				.field("username").equal(userFrom.getUsername())
				.retrievedFields(true, "users_followed");
		List<String> usersFollowedUsernames = this.find(getUsersFollowedQuery)
				.get().getUsersFollowed();
		Query<User> getUsersQuery = this.createQuery().field("username")
				.in(usersFollowedUsernames)
				.retrievedFields(true, "username", "verified");
		List<User> usersFollowed = this.find(getUsersQuery).asList();
		return usersFollowed;
	}
}
