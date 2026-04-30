package com.example.backend.exception;

public class GameStoppedException extends RuntimeException {
    public GameStoppedException(String message) {
        super(message);
    }
}