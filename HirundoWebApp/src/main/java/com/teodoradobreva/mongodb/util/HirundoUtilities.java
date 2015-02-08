package com.teodoradobreva.mongodb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HirundoUtilities {
	
	public static final int FOLLOWERS_NEEDED_FOR_VERIFIED = 10;
	public static final int MESSAGES_TO_GET_COUNT = 50;
	public static final int MAXIMUM_MESSAGE_SIZE = 140;
	
	public static String getHash(String plainText) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
		messageDigest.update(plainText.getBytes());
        byte hashedBytes[] = messageDigest.digest();
        StringBuffer hashed = new StringBuffer();
        for (int i = 0; i < hashedBytes.length; i++) {
        	hashed.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return hashed.toString();
	}
	
}
