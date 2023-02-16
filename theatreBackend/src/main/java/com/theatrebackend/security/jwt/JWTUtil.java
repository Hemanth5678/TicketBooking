package com.theatrebackend.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTUtil {

	@Value("${theatre.app.jwtSecret}")
    private String JWT_SECRET;
	
	@Value("${theatre.app.jwtExpirationInMs}")
	private String JWT_EXPIRATION;

    public String generateToken(String email) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("Authentication")
                .withClaim("email", email)
                .withIssuedAt(new Date())
//                .withExpiresAt(new Date(new Date().getTime()+JWT_EXPIRATION))
                .withIssuer("Hemanth A V")
                .sign(Algorithm.HMAC256(JWT_SECRET));
    }

    public String validateTokenAndRetrieveSubject(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET))
                .withSubject("Authentication")
                .withIssuer("Hemanth A V")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}