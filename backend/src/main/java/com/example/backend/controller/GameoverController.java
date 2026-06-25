package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.Gameover.GameoverResponse;
import com.example.backend.service.GameoverService;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class GameoverController {
    private GameoverService gameoverService;

    public GameoverController(GameoverService gameoverService) {
        this.gameoverService = gameoverService;
    }

    @GetMapping("/gameover")
    public GameoverResponse gameover(@RequestHeader("X-Session-Id") String sessionId) {
        return this.gameoverService.gameover(sessionId);
    }

    @PostMapping("/score/register")
    public Map<String, String> registerScore(@RequestBody Map<String, Integer> requestBody, @RequestHeader("X-Session-Id") String sessionId) {
        int score = requestBody.get("score");
        this.gameoverService.registerScore(score, sessionId);
        return Map.of("message", "ok");
    }
}
