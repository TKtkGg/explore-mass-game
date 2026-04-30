package com.example.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Map;

import com.example.backend.exception.GameStoppedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GameStoppedException.class)
    public ResponseEntity<Map<String, Object>> handleGameStoppedException(GameStoppedException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage(), "stopped", true));
    }
}