package com.bookstore.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    
    private String secret = "CheekoBoyee";

    private long expiration = 3600000;
//    
//    
//    public String extractUsername(String token) {
//		return extractClaims(token, Claims::getSubject);
//	}
//	
//	public Date extractExpiration(String token) {
//		return extractClaims(token, Claims::getExpiration);
//	}
//	
//	public Boolean isTokenExpired(String token) {
//		return extractExpiration(token).before(new Date());
//	}
//	
//	
//	// Validate JWT token
//    public boolean validateToken(String token, UserDetails userDetails) {
//    	final String username = extractUsername(token);
//    	try {
//            Claims claims = parseToken(token);
//            Date now = new Date();
//            return (username.equals(userDetails.getUsername()) && !claims.getExpiration().before(now));
//        } catch (Exception e) {
//            return false;
//        }
//    }
//	
//
//    // Generate a JWT token
//    public String generateToken(String username) {
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + expiration);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//    public <T> T extractClaims(String token,Function<Claims, T> claimsResolver) {
//		
//		final Claims claims = extractAllClaims(token);
//		return claimsResolver.apply(claims);
//    }
//
//    
//    public Claims extractAllClaims(String token) {
//		
//		return Jwts
//				.parser()
//				.setSigningKey(secret)
//				.parseClaimsJws(token)
//				.getBody();
//				
//	}
//    
//    // Parse JWT and get claims
//    public Claims parseToken(String token) {
//        return Jwts.parser()
//                .setSigningKey(secret)
//                .parseClaimsJws(token)
//                .getBody();
//    }

    
    public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public String generateToken(String username,String role) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("role", role);
		return createToken(claims, username); 
	}
	
	private String createToken(Map<String, Object> claims,String subject) {
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+expiration))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public <T> T extractClaims(String token,Function<Claims, T> claimsResolver) {
		
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
		
	}
	
	public Claims extractAllClaims(String token) {
		
		return Jwts
				.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
				
	}
	
	
}

