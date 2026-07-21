package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.backend.dto.Gameover.GameoverResponse;
import com.example.backend.service.GameoverService;
import com.example.backend.service.UserPrincipal;

import java.util.Map;

@RestController
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
    public Map<String, String> registerScore(
        @RequestHeader("X-Session-Id") String sessionId,
        @AuthenticationPrincipal UserPrincipal principal
    ) {
        this.gameoverService.registerScore(sessionId, principal);
        return Map.of("message", "ok");
    }
}
