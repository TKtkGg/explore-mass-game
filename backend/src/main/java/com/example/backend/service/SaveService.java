package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.service.gamestate.session.GameSession;

import tools.jackson.databind.ObjectMapper;

@Service
public class SaveService {
    private final ObjectMapper objectMapper;

    public SaveService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson(GameSession gameSession) throws Exception {
        return objectMapper.writeValueAsString(gameSession);
    }

    public GameSession fromJson(String json) throws Exception {
        return objectMapper.readValue(json, GameSession.class);
    }
}
