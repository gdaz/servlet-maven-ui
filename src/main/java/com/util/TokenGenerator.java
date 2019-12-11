package com.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class TokenGenerator {

	private static Logger logger = LoggerFactory.getLogger(TokenGenerator.class);
	private final static String _SECRET_KEY = "secretkey";

	private static Date toDate(LocalDateTime localDateTime) { 
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()); 
    } 
	public static JSONObject getDataFromToken(String token) throws Exception{
		JSONObject jsonClaim = new JSONObject();
		try {
			final Claims claims = Jwts.parser().setSigningKey(_SECRET_KEY).parseClaimsJws(token).getBody();
			for (String key : claims.keySet()) {
				logger.info("{} : {}", key, claims.get(key));
				jsonClaim.put(key, claims.get(key));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return jsonClaim;
	}
	private static Map getClaim(JSONObject json)throws Exception{
		Map tree = new TreeMap<String, Object>();
		Iterator<String> keys = (Iterator<String>) json.keys();
		while (keys.hasNext()) {
			String key = keys.next();
			tree.put(key, json.getString(key));
		}

		return tree;
	}
	public static String getToken(JSONObject json, long days, String subject) throws Exception{
		Map claims = getClaim(json);
		String jwtToken = Jwts.builder().setSubject(subject)
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusDays(days))).signWith(SignatureAlgorithm.HS256, _SECRET_KEY).compact();
		return jwtToken;
	}

	public static String getMinuteToken(JSONObject json, long minutes, String subject) throws Exception{
		
		Map claims = getClaim(json);
		String jwtToken = Jwts.builder().setSubject(subject)
				.setClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusMinutes(minutes))).signWith(SignatureAlgorithm.HS256, _SECRET_KEY).compact();
		return jwtToken;
	}

	public static String getTemporaryToken(JSONObject json) throws Exception {
		String result = null;
		String token = TokenGenerator.getMinuteToken(json, 10, "newuser");
//			result = "{\"token\":\""+token+"\",\"status\":true}";
		return new JSONObject().put("token", token).put("status", true).toString();
	}
	public static void main(String[] args) throws Exception{
		JSONObject json = new JSONObject();
		json.put("roles", "appuser");
		json.put("email", "user");
		json.put("userPK", "0");
		json.put("username", "999");
		String token = getToken(json,1,"appuser");
		System.out.println(token);
		final Claims claims = Jwts.parser().setSigningKey(_SECRET_KEY).parseClaimsJws(token).getBody();
		
		System.out.println(claims);
	   }//end main
}
