package com.viagens.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice 
public class GlobalExceptionHandler {
  
  /**
   * Handles EntityNotFoundException and returns a 404 Not Found response with an error message.
   *
   * @param ex the EntityNotFoundException
   * @return a ResponseEntity containing the error message and a 404 status code
   */
  @ExceptionHandler (EntityNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNotFound(EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
  }

  /**
   * Handles IllegalStateException and returns a 409 Conflict response with an error message.
   *
   * @param ex the IllegalStateException
   * @return a ResponseEntity containing the error message and a 409 status code
   */
  @ExceptionHandler (IllegalStateException.class)
  public ResponseEntity<Map<String, String>> handleConflict(IllegalStateException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", ex.getMessage()));
  }

  /**
   * Handles MethodArgumentNotValidException and returns a 400 Bad Request response with validation error messages.
   *
   * @param ex the MethodArgumentNotValidException
   * @return a ResponseEntity containing the validation error messages and a 400 status code
   */
  @ExceptionHandler (MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> { 
      errors.put(error.getField(), error.getDefaultMessage());
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }


}
