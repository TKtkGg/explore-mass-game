package com.example.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.service.BattleService;
import com.example.backend.dto.battle.BattleRequest;
import com.example.backend.dto.battle.BattleResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class BattleController {
    private BattleService battleService;

    public BattleController(BattleService battleService) {
        this.battleService = battleService;
    }

    @GetMapping("/battle")
    public BattleResponse battleStart() {
        return this.battleService.battleStart();
    }

    @PostMapping("/battle/action")
    public BattleResponse battle(@RequestBody BattleRequest request) {
        return this.battleService.battle(request);
    }
}
