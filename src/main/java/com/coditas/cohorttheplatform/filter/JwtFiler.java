package com.coditas.cohorttheplatform.filter;

import com.coditas.cohorttheplatform.entity.CohortUser;
import com.coditas.cohorttheplatform.exception.ExceptionMessages;
import com.coditas.cohorttheplatform.response.ApplicationResponse;
import com.coditas.cohorttheplatform.response.ErrorResponse;
import com.coditas.cohorttheplatform.service.CustomUserDetailsService;
import com.coditas.cohorttheplatform.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFiler extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper objectMapper;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {


        final String header = request.getHeader("Authorization");

        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = header.substring(7);
        String username;

        try {
            username = jwtUtil.extractUsername(token);
        }catch (JwtException e){
            response.setStatus(401);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            ErrorResponse errorResponse=new ErrorResponse(401, ExceptionMessages.JWT_TOKEN_EXPIRED, LocalDateTime.now());
            ApplicationResponse<List<ErrorResponse>> applicationResponse=new ApplicationResponse<>(List.of(errorResponse));
            response.getWriter().write(objectMapper.writeValueAsString(applicationResponse));
            return;
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            CohortUser cohortUser = customUserDetailsService.loadUserByUsername(username);

            if(jwtUtil.isValidToken(token, username)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        cohortUser,
                        null,
                        cohortUser.getAuthorities()
                );

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);


    }
}
