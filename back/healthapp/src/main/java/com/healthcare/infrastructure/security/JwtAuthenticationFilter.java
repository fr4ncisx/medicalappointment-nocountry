package com.healthcare.infrastructure.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.healthcare.infrastructure.security.service.AuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (!hasTokenAuthorization(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = getTokenFromHeader(request);
        authService.validateUserToken(token);
        filterChain.doFilter(request, response);
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
