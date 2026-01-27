package com.ibet.userservice.security.config;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtConfig jwtConfig;
    
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UserDetails userDetails, UUID userId, List<String> roles, List<String> permissions) {
        Map<String, Object> claims = Map.of(
            "userId", userId.toString(),
            "roles", roles,
            "permissions", permissions,
            "type", "ACCESS"
        );
        
        return generateToken(claims, userDetails.getUsername(), jwtConfig.getAccessTokenExpiration());
    }

    public String generateRefreshToken(UserDetails userDetails, UUID userId) {
        Map<String, Object> claims = Map.of(
            "userId", userId.toString(),
            "type", "REFRESH"
        );
        
        return generateToken(claims, userDetails.getUsername(), jwtConfig.getRefreshTokenExpiration());
    }

    private String generateToken(Map<String, Object> claims, String subject, Duration expiration) {
        Instant now = Instant.now();
        Instant expirationTime = now.plus(expiration);
        
        return Jwts.builder()
            .claims(claims)
            .subject(subject)
            .issuer(jwtConfig.getIssuer())
            .issuedAt(Date.from(now))
            .expiration(Date.from(expirationTime))
            .signWith(getSigningKey(), Jwts.SIG.HS256)
            .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public UUID getUserIdFromToken(String token) {
        String userId = getClaimFromToken(token, claims -> claims.get("userId", String.class));
        return userId != null ? UUID.fromString(userId) : null;
    }

    public List<String> getRolesFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("roles", List.class));
    }

    public String getTokenType(String token) {
        return getClaimFromToken(token, claims -> claims.get("type", String.class));
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public Authentication getAuthentication(String token) {
        String username = getUsernameFromToken(token);
        List<String> roles = getRolesFromToken(token);
        
        List<GrantedAuthority> authorities = roles.stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList());
        
        UserDetails userDetails = org.springframework.security.core.userdetails.User
            .withUsername(username)
            .password("")
            .authorities(authorities)
            .build();
        
        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
    }
}