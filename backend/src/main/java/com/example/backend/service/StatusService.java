package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.equipment.EquipmentRequest;
import com.example.backend.dto.equipment.EquipmentResponse;
import com.example.backend.dto.status.StatusResponse;
import com.example.backend.service.gamestate.session.GameSession;

@Service
public class StatusService {
    private GameSessionManager gameSessionManager;
    public StatusService(GameSessionManager gameSessionManager) {
        this.gameSessionManager = gameSessionManager;
    }
    public StatusResponse status(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);
        
        return new StatusResponse(gameSession.getPlayerState().getName(), gameSession.getPlayerState().getLevel(), gameSession.getPlayerState().getMaxHp(), gameSession.getPlayerState().getHp(), gameSession.getPlayerState().getOriginalAtk(), gameSession.getPlayerState().getAtk(), gameSession.getPlayerState().getDef(), gameSession.getPlayerState().getSpd(), gameSession.getPlayerState().getExp(), gameSession.getPlayerState().getNextLevelExp(), gameSession.getPlayerState().getGold(), gameSession.getPlayerState().getEquipment(), gameSession.getPlayerState().getOwnedEquipmentList(), gameSession.getPlayerState().getOwnedCards(), gameSession.getPlayerState().getOwnedItems());
    }

    public EquipmentResponse equipment(String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);

        return new EquipmentResponse(gameSession.getPlayerState().getOwnedEquipmentList(), gameSession.getPlayerState().getEquipment());
    }

    public EquipmentResponse changeEquipment(EquipmentRequest request, String sessionId) {
        GameSession gameSession = this.gameSessionManager.getRequiredGameSession(sessionId);

        gameSession.getPlayerState().setEquipment(gameSession.getPlayerState().getOwnedEquipmentList().stream().filter(e -> e.getName().equals(request.getName())).findFirst().orElse(null));
        return new EquipmentResponse(gameSession.getPlayerState().getOwnedEquipmentList(), gameSession.getPlayerState().getEquipment());
    }
}