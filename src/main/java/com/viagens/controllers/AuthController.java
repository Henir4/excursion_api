package com.viagens.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viagens.rotaunica.dto.AuthResponse;
import com.viagens.rotaunica.dto.LoginRequest;
import com.viagens.rotaunica.dto.RegisterRequest;
import com.viagens.rotaunica.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/auth")
@RequiredArgsConstructor 
public class AuthController {
  
  private final AuthService authService;

  @PostMapping ("/register")
  public ResponseEntity<AuthResponse> register(@Valid  @RequestBody RegisterRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(req));
  }

  @PostMapping ("/login")
  public AuthResponse login(@Valid @RequestBody LoginRequest req) {
    return authService.login(req);
  }
}
