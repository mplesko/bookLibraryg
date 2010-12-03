package com.logansrings.booklibrary.util

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import sun.misc.BASE64Encoder;
import groovy.transform.Synchronized

@Singleton 
class Encrypting {

	private MessageDigest messageDigest = MessageDigest.getInstance("SHA-1")

	/**
	* Encrypts aString
	* @param aString
	* @return an encrypted String or null if unable to encrypt aString
	*/
   @Synchronized
   String encrypt(String aString) {
	   String encryptedString = null
	   try {
//		   MessageDigest messageDigest = getMessageDigest()
		   messageDigest.reset()
		   messageDigest.update(aString.getBytes())
		   byte[] messageDigestBytes = messageDigest.digest()

		   encryptedString = (new BASE64Encoder()).encode(messageDigestBytes)
		   return encryptedString
	   }
	   catch (Exception e){
		   e.printStackTrace()
		   return null
	   }
   }

	static main(args) {
	
	}

}
