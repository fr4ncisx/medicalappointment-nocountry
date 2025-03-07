package com.healthcare.infrastructure.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.healthcare.infrastructure.security.service.AuthService;
import com.healthcare.infrastructure.security.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    //TODO: Work in progress to implement role interception
    //private final Set<String> PUBLIC_ENDPOINTS = Set.of("");

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!hasTokenAuthorization(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            var decodedJwt = jwtUtils.validateUserToken(getTokenFromHeader(request));
            authService.validateUser(decodedJwt);
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException | UsernameNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(
                String.format("{\"error\": \"%s\", \"message\": \"%s\"}", "JWT", ex.getMessage())
            );
        }
    }

    private boolean hasTokenAuthorization(HttpServletRequest request) {
        if (request.getHeader("Authorization") == null || request.getHeader("Authorization").isBlank()) {
            return false;
        }
        return true;
    }
    private String getTokenFromHeader(HttpServletRequest request){
        return request.getHeader("Authorization").replace("Bearer ", "");
    }
}
