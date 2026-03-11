package com.cls.erp.config;

import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	private String secret = "chaveSuperSecretaParaJwtDaClsErp123456789";
	
	private SecretKey SECRET_KEY;
	
    @PostConstruct
    public void init() {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes());
    }
	
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException, java.io.IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(SECRET_KEY)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.get("username", String.class);
                
                List<Map<String, Object>> rawPermissions = claims.get("permissions", List.class);
                
                List<SimpleGrantedAuthority> authorities = rawPermissions.stream()
                		.map(p -> new SimpleGrantedAuthority(p.get("developerName").toString()))
                		.toList();
                
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, authorities);

                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception ignored) {
            	System.out.println(ignored.getMessage());;
            }
        }
        chain.doFilter(request, response);
    }
}
