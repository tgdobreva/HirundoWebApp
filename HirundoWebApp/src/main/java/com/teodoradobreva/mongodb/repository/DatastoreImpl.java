package com.teodoradobreva.mongodb.repository;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class DatastoreImpl {
	private static Datastore datastore;
	
	public DatastoreImpl() throws UnknownHostException {
		Morphia morphia = new Morphia();
		DatastoreImpl.datastore = morphia.createDatastore(new MongoClient("localhost"), "hirundo");
		morphia.mapPackage("com.teodoradobreva.mongodb.model");
		datastore.ensureIndexes();
	}
	
	public static <T> void save(T object) {
		 datastore.save(object);
	}
}
