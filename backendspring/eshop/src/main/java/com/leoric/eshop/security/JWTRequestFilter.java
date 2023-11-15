package com.leoric.eshop.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.leoric.eshop.models.LocalUser;
import com.leoric.eshop.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
  private final JWTService jwtService;
  private final UserRepository userRepository;

  @Autowired
  public JWTRequestFilter(JWTService jwtService, UserRepository userRepository) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String tokenHeader = request.getHeader("Authorization");
    if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
      String token = tokenHeader.substring(7);
      try {
        String username = jwtService.getUsername(token);
        
        Optional<LocalUser> opUser = userRepository.findByUsername(username);
        if (opUser.isPresent()) {
          LocalUser user = opUser.get();
          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      } catch (JWTDecodeException ex) {

      }
    }
    filterChain.doFilter(request, response);
  }
}
