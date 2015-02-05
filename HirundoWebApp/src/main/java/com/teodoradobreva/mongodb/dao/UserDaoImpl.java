package com.teodoradobreva.mongodb.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.MongoClient;
import com.teodoradobreva.mongodb.model.User;


public class UserDaoImpl extends BasicDAO<User, ObjectId> implements UserDao {
	
	private Datastore datastore;

	protected UserDaoImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
		datastore = morphia.createDatastore(mongoClient, dbName);
		datastore.ensureIndexes();
	}

	@Override
	public boolean emailExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean usernameExists(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
