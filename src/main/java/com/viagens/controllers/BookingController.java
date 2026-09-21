package com.viagens.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viagens.rotaunica.dto.BookingRequest;
import com.viagens.rotaunica.model.Booking;
import com.viagens.rotaunica.model.User;
import com.viagens.rotaunica.services.BookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/bookings")
@RequiredArgsConstructor 
public class BookingController {

  private final BookingService bookingService;
  
  @PostMapping 
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<Booking> book (
    @Valid @RequestBody BookingRequest request,
    @AuthenticationPrincipal User user) {

    Booking booking = bookingService.createBooking(request, user); 

    return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }
}
