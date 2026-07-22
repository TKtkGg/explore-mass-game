package com.example.backend.service.gamestate.session;

import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.service.gamestate.MoveState;

public class GameSaveSnapshot {
    PlayerState playerState;
    MoveState moveState;

    public GameSaveSnapshot() {}
    
    public PlayerState getPlayerState() {
        return playerState;
    }
    public MoveState getMoveState() {
        return moveState;
    }
    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
    public void setMoveState(MoveState moveState) {
        this.moveState = moveState;
    }
}
