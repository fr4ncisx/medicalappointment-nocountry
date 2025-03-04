package com.healthcare.infrastructure.security.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.healthcare.infrastructure.dto.request.RequestLoginDTO;
import com.healthcare.infrastructure.dto.request.UserAuthenticated;

@Component
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private int expirationTime;
    @Value("${jwt.issuer}")
    private String appIssuer;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public <U extends UserAuthenticated> String generateToken(U user) {
        return JWT.create()
                .withIssuer(appIssuer)
                .withSubject(user.getEmail())
                .withIssuedAt(generateTimestamp(false))
                .withExpiresAt(generateTimestamp(true))
                .withClaim("role", user.getRole().toString())
                .withJWTId(generateRandomUUID())
                .sign(getAlgorithm());
    }

    public DecodedJWT validateToken(String token) {
        return JWT.require(getAlgorithm())
                .withIssuer(appIssuer)
                .build()
                .verify(token);
    }

    private String generateRandomUUID() {
        return UUID.randomUUID().toString();
    }

    private Instant generateTimestamp(boolean requireExpiration) {
        if (requireExpiration) {
            return LocalDateTime.now().plusMinutes(expirationTime).atZone(ZoneId.systemDefault()).toInstant();
        }
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant();
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC512(secretKey);
    }

    public <U extends UserAuthenticated> void passwordMatches(RequestLoginDTO requestFromLogin, U userFromRepository) {
        if (!passwordEncoder.matches(requestFromLogin.password(), userFromRepository.getPassword())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }
    }

    public void loadContext(UserAuthenticated user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        securityContext.setAuthentication(auth);
    }
}
