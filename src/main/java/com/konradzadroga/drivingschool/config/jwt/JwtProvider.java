package com.konradzadroga.drivingschool.config.jwt;

import com.konradzadroga.drivingschool.config.service.MyUserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration.time")
    private String tokenExpirationTime;

    public String generateToken(Authentication authentication) {
        MyUserDetails user = (MyUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 3600000))
                        //+ Integer.parseInt(tokenExpirationTime)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Expired token: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported token: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("String is empty: {}", e.getMessage());
        }
        return false;
    }

}
