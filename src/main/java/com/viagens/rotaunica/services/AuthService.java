package com.viagens.rotaunica.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.viagens.rotaunica.dto.AuthResponse;
import com.viagens.rotaunica.dto.LoginRequest;
import com.viagens.rotaunica.dto.RegisterRequest;
import com.viagens.rotaunica.model.User;
import com.viagens.rotaunica.model.UserRole;
import com.viagens.rotaunica.repositories.UserRepository;
import com.viagens.security.JwtService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Transactional 
  public AuthResponse register(RegisterRequest req) {
    if (userRepository.existsByEmail(req.email())) {
      throw new IllegalStateException("Email already exists");
    }

    User user = new User();
    user.setEmail(req.email());
    user.setPassword(passwordEncoder.encode(req.password()));
    user.setNickname(req.nickname());
    user.setRole(UserRole.CUSTOMER);

    userRepository.save(user);

    return new AuthResponse(jwtService.generateToken(user), user.getEmail(), user.getRole().name());
  }

  public AuthResponse login(LoginRequest req) {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(req.email(), req.password())
    );

    User user = userRepository.findByEmail(req.email())
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return new AuthResponse(jwtService.generateToken(user), user.getEmail(), user.getRole().name());
  }
}
