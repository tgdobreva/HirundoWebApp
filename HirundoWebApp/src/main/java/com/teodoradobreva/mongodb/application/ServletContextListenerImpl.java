package com.teodoradobreva.mongodb.application;

import java.net.UnknownHostException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.teodoradobreva.mongodb.repository.DatastoreImpl;

public class ServletContextListenerImpl implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		// close connection to database
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		try {
			DatastoreImpl datastoreImpl = new DatastoreImpl();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
