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
	public boolean verify(User user) {
		String hashedPassword = HirundoUtilities.getHash(user.getPassword());
		Query<User> query = this.createQuery().field("email").equal(user.getEmail())
				.field("password").equal(hashedPassword);
		boolean isRegistered = this.count(query) > 0;
		return isRegistered;
	}

}
