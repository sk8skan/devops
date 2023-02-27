package com.pichincha.demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerator {
	
	 private static final String SECRET_KEY = "secret";

	    public static void main(String[] args) {
	        String token = generateToken();
	        System.out.println(token);
	    }

	    public static String generateToken() {
	        String token = Jwts.builder()
	                .setSubject("jtorres") //
	                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
	                .compact();

	        return token;
	    }

}
