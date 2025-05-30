package com.payvantage.ethicainternetbanking.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final FetchUserDetailsFromDbService detailsFromDbService;

    public JwtFilter(JwtService jwtService, FetchUserDetailsFromDbService detailsFromDbService) {
        this.jwtService = jwtService;
        this.detailsFromDbService = detailsFromDbService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String emailAddress = null;

        if(authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
            emailAddress = jwtService.extractEmail(token);
        }

        if(emailAddress != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = detailsFromDbService.loadUserByUsername(emailAddress);
            if(jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken passwordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                passwordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
