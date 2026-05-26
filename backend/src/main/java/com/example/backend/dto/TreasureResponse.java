package com.example.backend.dto;

public class TreasureResponse {
    String message;

    public TreasureResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
