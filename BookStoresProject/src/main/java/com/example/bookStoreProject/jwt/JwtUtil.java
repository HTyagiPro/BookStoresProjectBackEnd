package com.example.bookStoreProject.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    
    private String secret = "CheekoBoyee";

    private long expiration = 3600000;
    
    
    public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaims(token, Claims::getExpiration);
	}
	
	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	
	// Validate JWT token
    public boolean validateToken(String token, UserDetails userDetails) {
    	final String username = extractUsername(token);
    	try {
            Claims claims = parseToken(token);
            Date now = new Date();
            return (username.equals(userDetails.getUsername()) && !claims.getExpiration().before(now));
        } catch (Exception e) {
            return false;
        }
    }
	

    // Generate a JWT token
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
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
    
    // Parse JWT and get claims
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    
    	
	
	
}

