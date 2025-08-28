package com.hackatonone.aprendamais.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                var jws = jwtUtil.parse(token);
                Claims claims = jws.getBody();
                String email = claims.getSubject();
                String role = claims.get("role", String.class);

                GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
                Authentication auth = new UsernamePasswordAuthenticationToken(email, null, List.of(authority));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception ignored) { /* Token inválido/expirado: segue anônimo */ }
        }

        chain.doFilter(req, res);
    }
}
