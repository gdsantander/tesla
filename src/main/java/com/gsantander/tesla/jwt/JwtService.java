package com.gsantander.tesla.jwt;

import java.security.Key;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.gsantander.tesla.tools.TslFunctions;
import org.joda.time.DateTime;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.gsantander.tesla.enums.TokenType;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String SECRET_KEY = "45871256325902102548036509804068745896526536";

    public String getToken(UserDetails userDetails, TokenType tokenType) {
        return this.getToken(new HashMap<>(),userDetails,tokenType);
    }

    private String getToken(Map<String,Object> extraClaims, UserDetails userDetails, TokenType tokenType) {
        DateTime dtIssuedAt = new DateTime();
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(dtIssuedAt.toDate())
                .setExpiration(dtIssuedAt.plusMinutes(tokenType.getExpirationInMinutes()).toDate())
                .signWith(this.getKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUserNameFromToken(String token) {
        return this.getClaim(token,Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = this.getUserNameFromToken(token);
        if(userName.equals(userDetails.getUsername()) && !this.isTokenExpired(token))
            return true;
        return false;
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpiration(String token) {
        return this.getClaim(token,Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return this.getExpiration(token).before(new Date());
    }

}
