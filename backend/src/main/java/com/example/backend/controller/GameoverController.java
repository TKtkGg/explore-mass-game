package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.Gameover.GameoverResponse;
import com.example.backend.service.GameoverService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class GameoverController {
    private GameoverService gameoverService;

    public GameoverController(GameoverService gameoverService) {
        this.gameoverService = gameoverService;
    }

    @GetMapping("/gameover")
    public GameoverResponse gameover() {
        return this.gameoverService.gameover();
    }
}
