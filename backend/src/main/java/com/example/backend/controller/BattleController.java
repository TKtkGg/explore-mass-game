package com.example.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.BattleService;
import com.example.backend.dto.battle.BattleRequest;
import com.example.backend.dto.battle.BattleResponse;

@RestController
public class BattleController {
    private BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @GetMapping("/battle")
    public BattleResponse battleStart(@RequestHeader("X-Session-Id") String sessionId) {
        return this.battleService.battleStart(sessionId);
    }

    @PostMapping("/battle/action")
    public BattleResponse battle(@RequestBody BattleRequest request, @RequestHeader("X-Session-Id") String sessionId) {
        return this.battleService.battle(request, sessionId);
    }
}
