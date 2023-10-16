package org.dargor.gateway.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtils {

    private static final String TOKEN_PREFIX = "Bearer";
    @Value("${jwt.secret}")
    private String jwtSecret;

    public Claims getClaims(final String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info("Error obtiendo claims: " + e.getMessage());
            throw e;
        }
    }

    public void validateToken(final String token) throws MalformedJwtException, UnsupportedJwtException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new MalformedJwtException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new MalformedJwtException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new MalformedJwtException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new MalformedJwtException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new UnsupportedJwtException("JWT claims string is empty");
        }
    }

    public String getToken(String header) {
        return header.substring(TOKEN_PREFIX.length() + 1);
    }
}
