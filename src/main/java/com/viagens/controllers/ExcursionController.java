package com.viagens.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viagens.rotaunica.dto.ExcursionRequest;
import com.viagens.rotaunica.dto.ExcursionResponse;
import com.viagens.rotaunica.model.User;
import com.viagens.rotaunica.services.ExcursionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/excursions")
@RequiredArgsConstructor 
public class ExcursionController {

  private final ExcursionService excursionService;
  
  @GetMapping
  public List<ExcursionResponse> getAll(
    @RequestParam(required = false) String location,
    @RequestParam(required = false) BigDecimal minPrice,
    @RequestParam(required = false) BigDecimal maxPrice) 
    
    { return excursionService.search(location, minPrice, maxPrice); }

  @GetMapping("/{id}")
  public ExcursionResponse getById(@PathVariable Long id) {
    return excursionService.findById(id);
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('GUIDE', 'ADMIN')")
  public ResponseEntity<ExcursionResponse> create

    (@Valid @RequestBody ExcursionRequest request,
     @AuthenticationPrincipal User guide) {

    ExcursionResponse response = excursionService.create(request, guide);

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
