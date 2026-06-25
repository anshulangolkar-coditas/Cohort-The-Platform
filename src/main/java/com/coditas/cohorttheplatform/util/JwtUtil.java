package com.coditas.cohorttheplatform.util;

import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.entity.RefreshToken;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.exception.InvalidRequestException;
import com.coditas.cohorttheplatform.exception.RefreshTokenExpiredException;
import com.coditas.cohorttheplatform.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtUtil {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private final Key key;

    public JwtUtil(@Value("${JWT_SECRET_KEY}")String secretKey){
        key = Keys.hmacShaKeyFor(secretKey.getBytes());

    }

    private static final long REFRESH_TOKEN_EXPIRY = 604800000L;
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

    @Transactional
    public String generateRefreshToken(CohortUser cohortUser){

        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .expiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRY))
                .cohortUser(cohortUser)
                .build();

        RefreshToken savedRefreshToken = refreshTokenRepository.save(refreshToken);

        return savedRefreshToken.getToken();
    }

    public boolean isRefreshTokenValid(RefreshToken refreshToken){

        if(refreshToken.getExpiresAt().before(new Date())){
            throw new InvalidRequestException(ExceptionMessages.REFRESH_TOKEN_EXPIRED);
        }
        return true;
    }
}
