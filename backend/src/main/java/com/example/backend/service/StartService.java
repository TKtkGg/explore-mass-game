package com.example.backend.service;

import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.service.gamestate.MoveState;

import org.springframework.stereotype.Service;

@Service
public class StartService {
    private PlayerState playerState;
    private MoveState moveState;

    public StartService(PlayerState playerState, MoveState moveState) {
        this.playerState = playerState;
        this.moveState = moveState;
    }

    public void start(String name) {
        this.playerState.init(name);
        this.reset();
    }

    public void reset() {
        this.moveState.setRemainingSteps(25);
        this.moveState.setStopped(false);
        this.moveState.setRouteType(null);
    }
}
