package com.amalemba.hotel_tour.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.security.SecretKeyBuilder;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Encoders;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 1 day in milliseconds

    // Define the algorithm
    private static final MacAlgorithm ALGORITHM = Jwts.SIG.HS256;

    // Todo[]: confirm if jwt key is working
    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    public void init() {
        // Convert the injected secret into a secret exactly once
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Long userId) {
        System.out.println("Secret: " + secret);
        return Jwts.builder()
                   .claims()
                   .subject(String.valueOf(userId))
                   .issuedAt(new Date())
                   .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                   .and()
                   .signWith(key, ALGORITHM)
                   .compact();
    }

    public String extractSubject(String token) {
        return Jwts.parser()
                   .verifyWith(key)
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();
    }

    public Long extractUserId(String token) {
        try {
            String subject = extractSubject(token);
            return Long.parseLong(subject);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isTokenValid(String token) {
        try {
            // Will throw an exception if invalid or expired
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
