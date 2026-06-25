package com.example.backend.service;

import com.example.backend.service.gamestate.MoveState;
import com.example.backend.service.gamestate.session.GameSession;

import org.springframework.stereotype.Service;

@Service
public class StartService {
    private GameSessionManager gameSessionManager;

    public StartService(GameSessionManager gameSessionManager) {
        this.gameSessionManager = gameSessionManager;
    }

    public void start(String name, String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);
        gameSession.getPlayerState().init(name);
        this.reset(gameSession.getMoveState());
    }

    public void reset(MoveState moveState) {
        moveState.setRemainingSteps(25);
        moveState.setStopped(false);
        moveState.setRouteType(null);
    }
}
