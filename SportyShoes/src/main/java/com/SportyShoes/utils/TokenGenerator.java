package com.SportyShoes.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {
	  public static String generateToken() {
	        try {
	            // Generate a random token using a secure hashing algorithm
	            MessageDigest digest = MessageDigest.getInstance("SHA-256");
	            byte[] hash = digest.digest(getRandomBytes());

	            // Encode the hash as a Base64 string
	            return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
	        } catch (NoSuchAlgorithmException e) {
	            // Handle exception if the specified algorithm is not available
	            e.printStackTrace();
	            return null;
	        }
	    }

	    private static byte[] getRandomBytes() {
	        // Generate a byte array with random values
	        byte[] bytes = new byte[32];
	        // Implement your logic to generate random bytes here
	        new SecureRandom().nextBytes(bytes);
	        return bytes;
	    }
}
