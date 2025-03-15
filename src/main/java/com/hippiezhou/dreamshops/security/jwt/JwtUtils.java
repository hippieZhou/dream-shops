package com.hippiezhou.dreamshops.security.jwt;

import com.hippiezhou.dreamshops.security.user.ShopUserDetails;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {
    @Value("${auth.token.jwtSecret}")
    private String jwtSecret;
    @Value("${auth.token.jwtExpirationMs}")
    private int jwtExpirationMs;

    // Generate a random private key
    public String generatePrivateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[64]; // 512 bits
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    public String generateTokenForUser(Authentication authentication) {
        ShopUserDetails userPrincipal = (ShopUserDetails) authentication.getPrincipal();

        List<String> roles = userPrincipal.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();
        return Jwts.builder()
            .setSubject(userPrincipal.getEmail())
            .claim("email", userPrincipal.getEmail())
            .claim("id", userPrincipal.getId())
            .claim("roles", roles)
            .claim("username", userPrincipal.getUsername())
            .setIssuer("dream-shops")
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(key(), SignatureAlgorithm.HS512).compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key())
            .build()
            .parseClaimsJws(token)
            .getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
    }
}
