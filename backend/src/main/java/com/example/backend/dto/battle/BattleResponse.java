package com.example.backend.dto.battle;

import com.example.backend.service.gamestate.BattleState;
import com.example.backend.service.gamestate.character.EnemyState;
import com.example.backend.service.gamestate.character.PlayerState;

public class BattleResponse {
    private String message;
    private PlayerState playerState;
    private EnemyState enemyState;
    private BattleState battleState;
    public BattleResponse(String message, PlayerState playerState, EnemyState enemyState, BattleState battleState) {
        this.message = message;
        this.playerState = playerState;
        this.enemyState = enemyState;
        this.battleState = battleState;
    }
    public String getMessage() {
        return message;
    }
    public PlayerState getPlayerState() {
        return playerState;
    }
    public EnemyState getEnemyState() {
        return enemyState;
    }
    public BattleState getBattleState() {
        return battleState;
    }
}
