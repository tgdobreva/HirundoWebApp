package com.teodoradobreva.mongodb.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.util.HirundoUtilities;

public class UserDaoImpl extends BasicDAO<User, ObjectId> implements UserDao {

	protected UserDaoImpl(MongoClient mongoClient, Morphia morphia,
			String dbName) {
		super(mongoClient, morphia, dbName);
		this.ensureIndexes();
	}

	@Override
	public User getUserByUsername(String username) {
		return this.createQuery().field("username").equal(username).get();
	}

	@Override
	public List<User> getAllUsersExcept(User user) {
		Query<User> getUsersQuery = this.createQuery().field("username")
				.notEqual(user.getUsername());
		return this.find(getUsersQuery).asList();
	}

	@Override
	public void follow(User follower, User followed) {
		follower = this.getUserByUsername(follower.getUsername());
		follower.getUsersFollowed().add(followed.getUsername());
		this.save(follower);
		this.setVerified(followed);
	}

	private void setVerified(User user) {
		user = this.getUserByUsername(user.getUsername());
		if (!user.isVerified()) {
			Query<User> getFollowersQuery = this.createQuery()
					.field("users_followed").equal(user.getUsername());
			long followersCount = this.count(getFollowersQuery);
			if (followersCount >= HirundoUtilities.FOLLOWERS_NEEDED_FOR_VERIFIED) {
				user.setVerified(true);
				this.save(user);
			}
		}
	}
}
