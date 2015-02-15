package com.teodoradobreva.mongodb.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.util.HirundoUtilities;

public class LoginDaoImpl extends BasicDAO<User, ObjectId> implements LoginDao {

	protected LoginDaoImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
		this.ensureIndexes();
	}

	@Override
	public User getUser(String email, String password) {
		String hashedPassword = HirundoUtilities.getHash(password);
		Query<User> query = this.createQuery().field("email").equal(email)
				.field("password").equal(hashedPassword);
		User user = this.findOne(query);
		return user;
	}

}
