package com.angel.server_spring_boot.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;
import com.angel.server_spring_boot.config.JwtProperties;
import java.util.Date;

@Service
public class JwtService {
    private final String SECRET_KEY;
    private final long EXPIRATION_TIME;

    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
        SECRET_KEY = jwtProperties.getSecret();
        EXPIRATION_TIME = jwtProperties.getExpiration();
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject(); // Return email if it is valid
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid token");
        }
    }
}
