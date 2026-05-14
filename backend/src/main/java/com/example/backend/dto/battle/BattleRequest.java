package com.example.backend.dto.battle;

import com.example.backend.domain.BattleChoice;

public class BattleRequest {
    BattleChoice playerChoice;
    public BattleRequest(BattleChoice playerChoice) {
        this.playerChoice = playerChoice;
    }
    public BattleChoice getPlayerChoice() {
        return playerChoice;
    }
}
