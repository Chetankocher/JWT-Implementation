package com.wepool.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	public static final String secret_key = "792F413F4428472B4B6250655368566D597133743677397A244326452948404D";

	public String extractUserName(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap(), userDetails);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSingingKey(), SignatureAlgorithm.HS256).compact();
	}
	
	
	public boolean isTokenValid(String token,UserDetails userDetails)
	{
		final String userName=extractUserName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token) );
	}
	
	
	public boolean isTokenExpired(String token) {
	
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token)
	{
		return extractClaims(token, Claims::getExpiration);
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSingingKey()).build().parseClaimsJws(token).getBody();

	}

	public Key getSingingKey() {
		byte[] key = Decoders.BASE64.decode(secret_key);
		return Keys.hmacShaKeyFor(key);
	}
}
