package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.item.ItemResponse;
import com.example.backend.service.gamestate.session.GameSession;

@Service
public class ItemService {
    private GameSessionManager gameSessionManager;
    public ItemService(GameSessionManager gameSessionManager) {
        this.gameSessionManager = gameSessionManager;
    }

    public ItemResponse showOwnedItems(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);
        
        return new ItemResponse(gameSession.getPlayerState().getOwnedItems());
    }
}
