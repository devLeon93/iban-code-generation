package com.icg.icgbackend.security.JWT;


import com.icg.icgbackend.security.UserCustomDetail;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JWTTokenProvider {

    @Value("${jwt.secret}")
    public String secret;

    @Value("${jwt.expiration_time}")
    private long expirationTime;

    public String generateToken(Authentication authentication) {
        UserCustomDetail userCustomDetail = (UserCustomDetail) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + expirationTime);

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("username", userCustomDetail.getUsername());

        return Jwts.builder()
                .setSubject(userCustomDetail.getAuthorities().toString())
                .addClaims(claimsMap)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException |
                 MalformedJwtException |
                 ExpiredJwtException |
                 UnsupportedJwtException |
                 IllegalArgumentException ex) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("username");
    }


}
