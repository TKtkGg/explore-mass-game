package com.example.backend.service;

import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import com.example.backend.service.gamestate.session.GameSession;
import com.example.backend.service.gamestate.character.PlayerState;
import com.example.backend.service.gamestate.MoveState;
import com.example.backend.service.gamestate.BattleState;
import com.example.backend.service.gamestate.treasure.TreasureState;
import com.example.backend.service.gamestate.shop.ShopState;
import com.example.backend.service.gamestate.equipment.EquipmentListState;

@Service
public class GameSessionManager {
    private Map<String, GameSession> gameSessions;

    public GameSessionManager() {
        this.gameSessions = new HashMap<>();
    }

    public GameSession createGameSession(String sessionId) {
        GameSession gameSession = new GameSession(sessionId, new PlayerState(new EquipmentListState()), new MoveState(), new BattleState(), new TreasureState(), new ShopState());
        gameSessions.put(sessionId, gameSession);
        return gameSession;
    }

    public GameSession getGameSession(String sessionId) {
        return gameSessions.get(sessionId);
    }

    public void removeGameSession(String sessionId) {
        gameSessions.remove(sessionId);
    }
}
