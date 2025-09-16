package com.exercicio.java.java.securityConfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    private Algorithm getAlgorithm() {

        return Algorithm.HMAC256(secretKey);
    }

    public String generateToken(String email) {

        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 40 * 1000))
                .sign(getAlgorithm());
    }

    public String extractUser(String token) {

        return JWT.require(
                getAlgorithm())
                .build()
                .verify(token)
                .getSubject();
    }

    public boolean validateToken(String token, String email) {

        DecodedJWT decodedJWT = JWT.require(getAlgorithm())
                .build()
                .verify(token);

        String getUser = decodedJWT.getSubject();
        Date expiretionData = decodedJWT.getExpiresAt();

        return decodedJWT != null
                && getUser.equals(email)
                && expiretionData != null
                && expiretionData.after(new Date());
    }
}
