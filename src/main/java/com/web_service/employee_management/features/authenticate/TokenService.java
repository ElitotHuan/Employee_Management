package com.web_service.employee_management.features.authenticate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Autowired
	TokenRepository repository;

	public TokenJWT createToken(TokenJWT token) {
		String accessToken = this.generate(token);

		token = new TokenJWT(accessToken, UUID.randomUUID().toString(), new Date(System.currentTimeMillis()),
				new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000));
		repository.save(token);

		return token;
	}

	public Boolean validateToken(String authToken) {
		Jwts.parser().setSigningKey("Secret_key").parseClaimsJws(authToken).getBody();
		return true;
	}

	public String getEmailFromJwt(String accessToken) {
		return Jwts.parser().setSigningKey("Secret_key").parseClaimsJws(accessToken).getBody().get("Email",
				String.class);
	}

	private String generate(TokenJWT token) {
		// Set claims. Contain only necessary information of the user
		Map<String, Object> claims = new HashMap<>();
		claims.put("Employee-id", token.getAccount().getEmployee().getId());
		claims.put("Email", token.getAccount().getEmail());

		// Create jwt token
		return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS512, "Secret_key").compact();
	}
	
	private void saveToken(TokenJWT token) {
		
	}

}
