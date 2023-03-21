package com.web_service.employee_management.features.authenticate;

import com.web_service.employee_management.execptions.customexceptions.ExpiredException;
import com.web_service.employee_management.execptions.customexceptions.RefreshTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {
    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    TokenRepository repository;

    public TokenJWT createToken(TokenJWT token) {
        String accessToken = this.generate(token);

        token.setAccessToken(accessToken);
        token.setRefreshToken(UUID.randomUUID().toString());
        token.setCreateDate(new Date(System.currentTimeMillis()));
        token.setExpDate(new Date(System.currentTimeMillis() + 5 * 60 * 60 * 1000));
        this.saveToken(token);

        return token;
    }

    public TokenJWT updateToken(String refreshToken) {
        TokenJWT token = repository.findByRefreshToken(refreshToken);

        if (token == null) {
            throw new RefreshTokenException("Token doesn't exist");
        }

        if (!token.getExpDate().before(new Date())) {
            String accessToken = this.generate(token);
            token.setAccessToken(accessToken);
            this.saveToken(token);
            return token;
        } else {
            throw new ExpiredException("Refresh token was expired. Please make a new signin request");
        }
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
        claims.put("Roles", token.getAccount().getRoles().toString());

        // Create jwt token
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 2 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "Secret_key").compact();
    }

    private void saveToken(TokenJWT token) {
        int checkUpdated = repository.updateToken(token);
        if (checkUpdated == 0) {
            repository.save(token);
        }
    }

}
