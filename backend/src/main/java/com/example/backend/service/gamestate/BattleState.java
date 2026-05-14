package com.example.backend.service.gamestate;

import com.example.backend.domain.BattleChoice;

import org.springframework.stereotype.Service;

@Service
public class BattleState {
    int currentTurns;
    int damage;
    BattleChoice playerChoice;
    BattleChoice enemyChoice;
    public BattleState(int currentTurns, int damage, BattleChoice playerChoice, BattleChoice enemyChoice) {
        this.currentTurns = currentTurns;
        this.damage = damage;
        this.playerChoice = playerChoice;
        this.enemyChoice = enemyChoice;
    }
    public int getCurrentTurns() {
        return currentTurns;
    }
    public int getDamage() {
        return damage;
    }
    public BattleChoice getPlayerChoice() {
        return playerChoice;
    }
    public BattleChoice getEnemyChoice() {
        return enemyChoice;
    }

    public void setCurrentTurns(int currentTurns) {
        this.currentTurns = currentTurns;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public void setPlayerChoice(BattleChoice playerChoice) {
        this.playerChoice = playerChoice;
    }
    public void setEnemyChoice(BattleChoice enemyChoice) {
        this.enemyChoice = enemyChoice;
    }
}
