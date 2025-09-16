package com.exercicio.java.java.securityConfig;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
               
        if (request.getRequestURI().equals("/users/register") || request.getRequestURI().equals("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        if (header == null || !header.startsWith("Bearer ")) {            
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token ausente!\"}");
            return;
        } 
        
        String token = header.substring(7);
        try {

            String email = jwtUtil.extractUser(token);
        
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                
                UserDetails user = userDetailsService.loadUserByUsername(email);
                boolean validToken = jwtUtil.validateToken(token, user.getUsername());
                
                if (validToken) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                    );
                    
                    authentication.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request)
                    );
                    
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }  
        } catch (TokenExpiredException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token expirado, faça login novamente!\"}");
            return;

        } catch(SignatureVerificationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token incorreto\"}");
            return;

        } 
       filterChain.doFilter(request, response);
    }
}
