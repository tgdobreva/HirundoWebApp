package com.teodoradobreva.mongodb.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.MongoClient;
import com.teodoradobreva.mongodb.model.User;
import com.teodoradobreva.mongodb.util.HirundoUtilities;


public class RegistrationDaoImpl extends BasicDAO<User, ObjectId> implements RegistrationDao {

	protected RegistrationDaoImpl(MongoClient mongoClient, Morphia morphia, String dbName) {
		super(mongoClient, morphia, dbName);
		this.ensureIndexes();
	}

	@Override
	public boolean emailExists(String email) {
		return this.findOne("email", email) != null;
	}

	@Override
	public boolean usernameExists(String username) {
		return this.findOne("username", username) != null;
	}

	@Override
	public void register(User user) {
		User userToStore = new User(user);
		String hashedPassword = HirundoUtilities.getHash(userToStore.getPassword());
		userToStore.setPassword(hashedPassword);
		this.save(userToStore);
	}
	
}
