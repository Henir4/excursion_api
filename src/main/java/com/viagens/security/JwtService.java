package com.viagens.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service 
public class JwtService {
  
  @Value ("${app.jwt.secret}")
  private String secret;

  @Value ("${app.jwt.expiration-ms}")
  private Long expirationMs;

  private SecretKey key() {
    return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  public String generateToken(UserDetails user) {
    Date now = new Date();
    return Jwts.builder()
      .subject(user.getUsername())
      .issuedAt(now)
      .expiration(new Date(now.getTime() + expirationMs))
      .signWith(key())
      .compact();
  }

  public String extractUsername(String token) {
    return parse(token).getPayload().getSubject();
  }

  public boolean isValid(String token, UserDetails user) {
    try {

      Claims claims = parse(token).getPayload();
      return claims.getSubject().equals(user.getUsername())
       && claims.getExpiration().after(new Date());
       
    } catch (JwtException | IllegalArgumentException e) {
      return false;
    }
  }

  private Jws<Claims> parse(String token) {
    return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token);
  }
}
