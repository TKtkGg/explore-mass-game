package com.example.backend.service.gamestate;

import com.example.backend.domain.BattleChoice;

import org.springframework.stereotype.Service;

@Service
public class BattleState {
    int currentTurns;
    int damage;
    boolean finished;
    BattleChoice playerChoice;
    BattleChoice enemyChoice;
    public BattleState() {
        this.currentTurns = 0;
        this.damage = 0;
        this.finished = false;
        this.playerChoice = null;
        this.enemyChoice = null;
    }
    public int getCurrentTurns() {
        return currentTurns;
    }
    public int getDamage() {
        return damage;
    }
    public boolean getFinished() {
        return finished;
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
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void reset() {
        this.currentTurns = 0;
        this.damage = 0;
        this.finished = false;
        this.playerChoice = null;
        this.enemyChoice = null;
    }
}
