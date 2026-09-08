package com.medibase.integration.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${integration.api.key}")
    private String integrationApiKey;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        return !request.getRequestURI()
                .startsWith("/api/v1/integration/");
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String apiKey = request.getHeader("x-api-key");


        if (apiKey == null || !apiKey.equals(integrationApiKey)) {

            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            response.setContentType("application/json");

            response.getWriter().write(
                    "{\"message\":\"Invalid or missing API key\"}"
            );

            return;
        }


        filterChain.doFilter(request, response);
    }
}
