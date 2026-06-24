package com.coditas.cohorttheplatform.util;

import com.coditas.cohorttheplatform.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtUtil {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private String secretKey;
    private final Key key;

    public JwtUtil(@Value("${JWT_SECRET_KEY}")String secretKey){
        key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.secretKey = secretKey;

    }

    private static final Long REFRESH_TOKEN_EXPIRY = 30L; // days
    private static final Long EXPIRATION_TIME = 1000L * 60L * 10L;

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean isValidToken(String token, String username){
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    public String generateRefreshToken(Users user){

        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .expiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRY))
                .user(user)
                .build();

        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);

        return savedRefreshToken.getToken();
    }

    public boolean isRefreshTokenValid(RefreshToken refreshToken){

        if(refreshToken.getExpiresAt().before(new Date())){
            throw new RefreshTokenExpiredException(ExceptionMessages.REFRESH_TOKEN_EXPIRED);
        }
        return true;
    }


}
