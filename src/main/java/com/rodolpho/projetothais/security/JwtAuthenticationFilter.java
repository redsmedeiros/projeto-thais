package com.rodolpho.projetothais.security;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService){

        this.jwtTokenProvider = jwtTokenProvider;

        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                //get token from request
                String token = getTokenFromRequest(request);

                //validate token
                if(StringUtils.isNotEmpty(token) && jwtTokenProvider.validateToken(token)){
                    
                }
        
    }

    private String getTokenFromRequest(HttpServletRequest request){

        String bearerToken = request.getHeader("Authorization");

        if(!StringUtils.isEmpty(bearerToken) && bearerToken.startsWith("Bearer")){      
            
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;

    }
    
}
