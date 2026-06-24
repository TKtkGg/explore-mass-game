package com.example.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.GameSessionManager;
import com.example.backend.service.StartService;
import com.example.backend.dto.StartRequest;

import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class StartController {
    private StartService startService;
    private GameSessionManager gameSessionManager;

    public StartController(StartService startService, GameSessionManager gameSessionManager) {
        this.startService = startService;
        this.gameSessionManager = gameSessionManager;
    }

    @PostMapping("/start")
    public Map<String, String> start(@RequestBody StartRequest request) {
        String sessionId = UUID.randomUUID().toString();
        this.gameSessionManager.createGameSession(sessionId);
        this.startService.start(request.getName());
        return Map.of("sessionId", sessionId);
    }
}
