package com.select.choice.global.security;

import com.select.choice.global.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;

    private final long ACCESS_TOKEN_EXPIRED_TIME = 2 * 60 * 10000; // 2시간
    private final long REFRESH_TOKEN_EXPIRED_TIME = 7 * 24 * 60 * 60 * 1000; // 1주

    @AllArgsConstructor
    enum TokenType{
        ACCESS_TOKEN("accessToken"),
        REFRESH_TOKEN("refreshToken");

        private final String value;
    }

    public Key getSignInKey(String key){
        byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException, IllegalStateException, UnsupportedOperationException {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey(jwtProperties.getKey()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String createToken(String userPk, TokenType tokenType, Long expiredTime) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("tokenType", tokenType.value);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +  expiredTime))
                .signWith(getSignInKey(jwtProperties.getKey()))
                .compact();
    }

    public LocalDateTime getExpiredTime() {
        return LocalDateTime.now().plusSeconds(ACCESS_TOKEN_EXPIRED_TIME/1000);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearToken = request.getHeader("Authorization");
        if(bearToken != null && bearToken.startsWith("bearer ")){
            return bearToken.substring(7);
        } else {
            return null;
        }
    }

    public String getUserPk(String token){
        String refreshToken = token.replace("Bearer", "");
        return extractAllClaims(refreshToken).getSubject();
    }

    public String generateAccessToken(String userPk) {
        return createToken(userPk, TokenType.ACCESS_TOKEN, ACCESS_TOKEN_EXPIRED_TIME);
    }

    public String generateRefreshToken(String userPk) {
        return createToken(userPk, TokenType.REFRESH_TOKEN, REFRESH_TOKEN_EXPIRED_TIME);
    }

    public boolean validateToken(String token){
        try {
            extractAllClaims(token).getExpiration();
            return false;
        } catch (Exception e){
            return  true;
        }
    }
}
