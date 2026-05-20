package com.example.backend.dto.battle;

import com.example.backend.domain.BattleChoice;

public class BattleRequest {
    BattleChoice playerChoice;
    String itemName;
    public BattleRequest(BattleChoice playerChoice, String itemName) {
        this.playerChoice = playerChoice;
        this.itemName = itemName;
    }
    public BattleChoice getPlayerChoice() {
        return playerChoice;
    }
    public String getItemName() {
        return itemName;
    }
}
