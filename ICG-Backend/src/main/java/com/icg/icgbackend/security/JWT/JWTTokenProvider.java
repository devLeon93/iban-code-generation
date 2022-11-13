package com.icg.icgbackend.security.JWT;


import com.icg.icgbackend.exception.UserNotFoundException;
import com.icg.icgbackend.model.User;
import com.icg.icgbackend.repository.UserRepository;
import com.icg.icgbackend.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class JWTTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JWTTokenProvider.class);


    private final UserRepository userRepository;

    @Value("${test.app.jwtSecret}")
    private String jwtSecret;

    @Value("${test.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(User user) {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id",user.getId());
        claims.put("email",user.getEmail());
        claims.put("role",user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList()));


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
