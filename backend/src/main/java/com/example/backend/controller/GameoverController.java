package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.example.backend.dto.Gameover.GameoverResponse;
import com.example.backend.service.GameoverService;
import com.example.backend.service.SaveService;
import com.example.backend.service.UserPrincipal;

import java.util.Map;

@RestController
public class GameoverController {
    private GameoverService gameoverService;
    private SaveService saveService;

    public GameoverController(GameoverService gameoverService, SaveService saveService) {
        this.gameoverService = gameoverService;
        this.saveService = saveService;
    }

    @GetMapping("/gameover")
    public GameoverResponse gameover(
        @RequestHeader("X-Session-Id") String sessionId,
        @AuthenticationPrincipal UserPrincipal principal
    ) {
        GameoverResponse response = gameoverService.gameover(sessionId);
        if (principal != null) {
            saveService.deleteByUserId(principal.getUserId());
        }
        return response;
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
