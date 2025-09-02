package com.seguradora.paocomovo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.seguradora.paocomovo.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")

    private String secretUrl;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
            String token = JWT.create().withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")))
                    .sign(algorithm);

            return token = token.replace("Bearer ", "");
        } catch (JWTCreationException e) {
            throw new RuntimeException("JWT creation exception");
        }
    }

    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(secretUrl))
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
